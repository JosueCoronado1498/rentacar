package com.jcoronado.rentacar.rentals.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class RentalRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;


}
