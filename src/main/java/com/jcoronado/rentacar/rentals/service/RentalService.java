package com.jcoronado.rentacar.rentals.service;

import com.jcoronado.rentacar.common.enums.RentalStatus;
import com.jcoronado.rentacar.common.enums.VehicleStatus;
import com.jcoronado.rentacar.rentals.model.Rental;
import com.jcoronado.rentacar.rentals.repository.IRentalRepository;
import com.jcoronado.rentacar.users.model.User;
import com.jcoronado.rentacar.users.service.IUserService;
import com.jcoronado.rentacar.vehicles.model.Vehicle;
import com.jcoronado.rentacar.vehicles.services.IVehicleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService implements IRentalService{

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IVehicleService vehicleService;

    @Override
    public Rental createRental(Long userId, Long vehicleId, LocalDate startDate, LocalDate endDate) {
        if(endDate.isBefore(startDate)){
            throw new IllegalArgumentException("Invalid rental dates");
        }

        boolean vehicleBusy = rentalRepository.existsByVehicleIdAndStatusIn(vehicleId,
                List.of(RentalStatus.ACTIVE, RentalStatus.RESERVED));

        if(vehicleBusy) {
            throw new IllegalArgumentException("Vehicle not available");
        }

        User user = userService.findById(userId);
        Vehicle vehicle = vehicleService.findById(vehicleId);

        long days = ChronoUnit.DAYS.between(startDate,endDate) + 1;

        BigDecimal totalPrice = vehicle.getCategory().getDailyPrice().multiply(BigDecimal.valueOf(days));

        vehicle.setStatus(VehicleStatus.RENTED);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setVehicle(vehicle);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setStatus(RentalStatus.ACTIVE);
        rental.setTotalPrice(totalPrice);

        return rentalRepository.save(rental);

    }

    @Override
    public Rental finishRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        rental.setStatus(RentalStatus.FINISHED);
        rental.getVehicle().setStatus(VehicleStatus.AVAILABLE);

        return rental;
    }
}
