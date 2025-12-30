package com.jcoronado.rentacar.vehicles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class VehicleResponse {

    private Long id;
    private String plate;
    private String brand;
    private String model;
    private int year;
    private String status;

}
