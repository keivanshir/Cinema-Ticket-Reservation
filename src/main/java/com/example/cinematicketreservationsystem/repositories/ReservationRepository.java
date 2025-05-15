package com.example.cinematicketreservationsystem.repositories;

import com.example.cinematicketreservationsystem.entities.MovieShow;
import com.example.cinematicketreservationsystem.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
    boolean existsReservationByMovieShowAndSeatNumber(MovieShow movieShow, Integer seatNumber);
}
