package com.example.cinematicketreservationsystem.repositories;

import com.example.cinematicketreservationsystem.entities.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Long> , JpaSpecificationExecutor<MovieShow> {

}
