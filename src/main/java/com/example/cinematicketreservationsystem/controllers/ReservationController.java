package com.example.cinematicketreservationsystem.controllers;

import com.example.cinematicketreservationsystem.dtos.ReservationDto;
import com.example.cinematicketreservationsystem.dtos.Response;
import com.example.cinematicketreservationsystem.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Response<ReservationDto>> reserveSeat(@RequestBody ReservationDto reservationDto){
        return new ResponseEntity<>(reservationService.reserveSeat(reservationDto), HttpStatus.OK);
    }
}
