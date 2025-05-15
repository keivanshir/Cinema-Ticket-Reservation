package com.example.cinematicketreservationsystem.services;

import com.example.cinematicketreservationsystem.dtos.MovieShowDto;
import com.example.cinematicketreservationsystem.dtos.Response;

import java.util.List;

public interface MovieShowService {

    Response<MovieShowDto> createMovieShow(MovieShowDto movieShowDto);
    Response<List<MovieShowDto>> getMovieShows(int pageNumber, int pageSize);
}
