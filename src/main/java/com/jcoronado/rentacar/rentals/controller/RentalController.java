package com.jcoronado.rentacar.rentals.controller;

import com.jcoronado.rentacar.rentals.dto.RentalRequest;
import com.jcoronado.rentacar.rentals.dto.RentalResponse;
import com.jcoronado.rentacar.rentals.model.Rental;
import com.jcoronado.rentacar.rentals.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalResponse> createRental(@Valid @RequestBody RentalRequest request){

        Rental rental = rentalService.createRental(
                request.getUserId(),
                request.getVehicleId(),
                request.getStartDate(),
                request.getEndDate()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RentalResponse(
                        rental.getId(),
                        rental.getUser().getId(),
                        rental.getVehicle().getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getTotalPrice(),
                        rental.getStatus().name()
                ));
    }

    @PostMapping("/{id}/finish")
    public RentalResponse finishRental(@PathVariable Long id){

        Rental rental = rentalService.finishRental(id);

        return new RentalResponse(
                rental.getId(),
                rental.getUser().getId(),
                rental.getVehicle().getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getTotalPrice(),
                rental.getStatus().name()
        );
    }


}
