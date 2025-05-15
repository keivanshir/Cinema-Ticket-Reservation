package com.example.cinematicketreservationsystem.services;

import com.example.cinematicketreservationsystem.dtos.EntityMapper;
import com.example.cinematicketreservationsystem.dtos.ReservationDto;
import com.example.cinematicketreservationsystem.dtos.Response;
import com.example.cinematicketreservationsystem.entities.MovieShow;
import com.example.cinematicketreservationsystem.entities.Reservation;
import com.example.cinematicketreservationsystem.exception.SeatNotAvailableException;
import com.example.cinematicketreservationsystem.repositories.MovieShowRepository;
import com.example.cinematicketreservationsystem.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReservationServiceImplementation implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final MovieShowRepository movieShowRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository,
                                            MovieShowRepository movieShowRepository) {
        this.reservationRepository = reservationRepository;
        this.movieShowRepository = movieShowRepository;
    }

    @Override
    public Response<ReservationDto> reserveSeat(ReservationDto reservationDto) {
        MovieShow movieShow = movieShowRepository.findById(reservationDto.getMovieShowDto().getId())
                .orElseThrow(() -> new EntityNotFoundException("فیلم پیدا نشد!"));

        if (movieShow.getAvailableSeats() <= 0){
            throw new SeatNotAvailableException("تعداد جاهای خالی به پایان رسیده است!");
        }

        if (reservationRepository.existsReservationByMovieShowAndSeatNumber(movieShow,
                reservationDto.getSeatNumber())){
            throw new SeatNotAvailableException("صندلی قبلاً رزرو شده است!");
        }

        movieShow.setAvailableSeats(movieShow.getAvailableSeats() - 1);
        movieShowRepository.save(movieShow);

        Reservation reservation = new Reservation();
        reservation.setUserId(reservationDto.getUserId());
        reservation.setMovieShow(movieShow);
        reservation.setSeatNumber(reservationDto.getSeatNumber());
        Reservation savedReservation = reservationRepository.save(reservation);

        return Response.<ReservationDto>builder()
                .message("صندلی مورد نظر رزرو شد!")
                .data(EntityMapper.toReservationDto(savedReservation))
                .build();
    }
}
