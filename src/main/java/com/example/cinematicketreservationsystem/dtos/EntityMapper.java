package com.example.cinematicketreservationsystem.dtos;

import com.example.cinematicketreservationsystem.entities.MovieShow;
import com.example.cinematicketreservationsystem.entities.Reservation;

public class EntityMapper {

    public static MovieShow toMovieShowEntity(MovieShowDto movieShowDto){
        MovieShow movieShow = new MovieShow();
        movieShow.setId(movieShowDto.getId() != null ?
                movieShowDto.getId() : movieShow.getId());
        movieShow.setMovieName(movieShowDto.getMovieName() != null ?
                movieShowDto.getMovieName() : movieShow.getMovieName());
        movieShow.setStartTime(movieShowDto.getStartTime() != null ?
                movieShowDto.getStartTime() : movieShow.getStartTime());
        movieShow.setSeatCount(movieShowDto.getSeatCount() != null?
                movieShowDto.getSeatCount() : movieShow.getSeatCount());
        movieShow.setAvailableSeats(movieShowDto.getAvailableSeats() != null?
                movieShowDto.getAvailableSeats() : movieShow.getAvailableSeats());
        return movieShow;
    }

    public static MovieShowDto toMovieShowDto(MovieShow movieShow){
        MovieShowDto movieShowDto = new MovieShowDto();
        movieShowDto.setId(movieShow.getId() != null ?
                movieShow.getId() : movieShowDto.getId());
        movieShowDto.setMovieName(movieShow.getMovieName() != null ?
                movieShow.getMovieName() : movieShowDto.getMovieName());
        movieShowDto.setStartTime(movieShow.getStartTime() != null ?
                movieShow.getStartTime() : movieShowDto.getStartTime());
        movieShowDto.setSeatCount(movieShow.getSeatCount() != null?
                movieShow.getSeatCount() : movieShowDto.getSeatCount());
        movieShowDto.setAvailableSeats(movieShow.getAvailableSeats() != null?
                movieShow.getAvailableSeats() : movieShowDto.getAvailableSeats());
        return movieShowDto;
    }

    public static Reservation toReservationEntity(ReservationDto reservationDto){
        Reservation reservation = new Reservation();
        reservation.setMovieShow(toMovieShowEntity(reservationDto.getMovieShowDto()));
        reservation.setSeatNumber(reservationDto.getSeatNumber());
        reservation.setUserId(reservationDto.getUserId());
        return reservation;
    }

    public static ReservationDto toReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setMovieShowDto(toMovieShowDto(reservation.getMovieShow()));
        reservationDto.setSeatNumber(reservation.getSeatNumber());
        reservationDto.setUserId(reservation.getUserId());
        return reservationDto;
    }
}
