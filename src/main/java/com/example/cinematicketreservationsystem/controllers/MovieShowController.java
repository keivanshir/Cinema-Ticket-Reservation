package com.example.cinematicketreservationsystem.controllers;

import com.example.cinematicketreservationsystem.dtos.MovieShowDto;
import com.example.cinematicketreservationsystem.dtos.Response;
import com.example.cinematicketreservationsystem.services.MovieShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class MovieShowController {

    private final MovieShowService movieShowService;

    public MovieShowController(MovieShowService movieShowService) {
        this.movieShowService = movieShowService;
    }

    @GetMapping
    public ResponseEntity<Response<List<MovieShowDto>>> getMovieShows(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize){
        return new ResponseEntity<>(movieShowService.getMovieShows(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<MovieShowDto>> createMovieShow(@RequestBody MovieShowDto movieShowDto){
        return new ResponseEntity<>(movieShowService.createMovieShow(movieShowDto), HttpStatus.CREATED);
    }
}
