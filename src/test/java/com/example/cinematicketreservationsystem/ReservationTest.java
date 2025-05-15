package com.example.cinematicketreservationsystem;

import com.example.cinematicketreservationsystem.dtos.MovieShowDto;
import com.example.cinematicketreservationsystem.dtos.ReservationDto;
import com.example.cinematicketreservationsystem.dtos.Response;
import com.example.cinematicketreservationsystem.entities.MovieShow;
import com.example.cinematicketreservationsystem.repositories.MovieShowRepository;
import com.example.cinematicketreservationsystem.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReservationTest {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Test
    public void testConcurrentReservations() throws InterruptedException, ExecutionException {
        MovieShow show = new MovieShow();
        show.setMovieName("Avengers");
        show.setStartTime(LocalDateTime.now().plusDays(1));
        show.setSeatCount(100);
        show.setAvailableSeats(100);
        show = movieShowRepository.save(show);

        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<Future<Response<ReservationDto>>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            final int seatNumber = i + 1;
            MovieShow finalShow = show;
            futures.add(executor.submit(() -> {
                try {
                    latch.countDown();
                    latch.await();

                    ReservationDto request = new ReservationDto();
                    request.setUserId("user-" + seatNumber);
                    MovieShowDto movieShowDto = new MovieShowDto();
                    movieShowDto.setId(finalShow.getId());
                    request.setMovieShowDto(movieShowDto);
                    request.setSeatNumber(seatNumber);

                    return reservationService.reserveSeat(request);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // Verify all reservations were successful
        for (Future<Response<ReservationDto>> future : futures) {
            assertNotNull(future.get());
        }

        // Verify no seats are overbooked
        MovieShow updatedShow = movieShowRepository.findById(show.getId()).get();
        assertEquals(0, updatedShow.getAvailableSeats());
    }
}