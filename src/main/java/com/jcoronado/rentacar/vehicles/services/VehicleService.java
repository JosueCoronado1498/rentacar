package com.jcoronado.rentacar.vehicles.services;

import com.jcoronado.rentacar.common.enums.VehicleStatus;
import com.jcoronado.rentacar.vehicles.model.Vehicle;
import com.jcoronado.rentacar.vehicles.repository.IVehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        if(vehicleRepository.existsByPlate(vehicle.getPlate())){
            throw new IllegalArgumentException("Plate already exists");
        }
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByStatus(VehicleStatus.AVAILABLE);
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
    }
}
