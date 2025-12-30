package com.jcoronado.rentacar.rentals.service;

import com.jcoronado.rentacar.rentals.model.Rental;

import java.time.LocalDate;

public interface IRentalService {

    public Rental createRental(Long userId, Long vehicleId, LocalDate startDate, LocalDate endDate);

    public Rental finishRental(Long rentalId);

}
