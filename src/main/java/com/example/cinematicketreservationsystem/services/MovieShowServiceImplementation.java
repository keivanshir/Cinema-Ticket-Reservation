package com.example.cinematicketreservationsystem.services;

import com.example.cinematicketreservationsystem.dtos.EntityMapper;
import com.example.cinematicketreservationsystem.dtos.MovieShowDto;
import com.example.cinematicketreservationsystem.dtos.Response;
import com.example.cinematicketreservationsystem.entities.MovieShow;
import com.example.cinematicketreservationsystem.repositories.MovieShowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieShowServiceImplementation implements MovieShowService{

    private final MovieShowRepository movieShowRepository;

    public MovieShowServiceImplementation(MovieShowRepository movieShowRepository) {
        this.movieShowRepository = movieShowRepository;
    }

    @Override
    public Response<MovieShowDto> createMovieShow(MovieShowDto movieShowDto) {
        MovieShow movieShow = EntityMapper.toMovieShowEntity(movieShowDto);
        MovieShowDto savedMovieShow = EntityMapper.toMovieShowDto(movieShowRepository.save(movieShow));

        return Response.<MovieShowDto>builder()
                .message("اطلاعات نمایش فیلم ثبت شد")
                .data(savedMovieShow)
                .build();
    }

    @Override
    public Response<List<MovieShowDto>> getMovieShows(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<MovieShow> movieShowPage = movieShowRepository.findAll(pageable);

        return Response.<List<MovieShowDto>>builder()
                .message("همه ی فیلم ها:")
                .data(movieShowPage.getContent().stream()
                        .map(EntityMapper::toMovieShowDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
