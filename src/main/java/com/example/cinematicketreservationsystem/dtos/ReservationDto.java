package com.example.cinematicketreservationsystem.dtos;

public class ReservationDto {
    private String userId;
    private MovieShowDto movieShowDto;
    private Integer seatNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public MovieShowDto getMovieShowDto() {
        return movieShowDto;
    }

    public void setMovieShowDto(MovieShowDto movieShowDto) {
        this.movieShowDto = movieShowDto;
    }
}
