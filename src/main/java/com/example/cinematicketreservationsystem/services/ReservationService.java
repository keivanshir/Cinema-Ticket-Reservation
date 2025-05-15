package com.example.cinematicketreservationsystem.services;

import com.example.cinematicketreservationsystem.dtos.ReservationDto;
import com.example.cinematicketreservationsystem.dtos.Response;

public interface ReservationService {
    Response<ReservationDto> reserveSeat(ReservationDto reservationDto);
}
