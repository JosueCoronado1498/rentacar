package com.jcoronado.rentacar.vehicles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class VehicleCategoryRequest {

    @NotBlank
    private String categoryName;

    @NotNull
    @Positive
    private BigDecimal dailyPrice;

}
