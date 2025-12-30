package com.jcoronado.rentacar.vehicles.services;

import com.jcoronado.rentacar.vehicles.model.Vehicle;

import java.util.List;

public interface IVehicleService {

    public Vehicle createVehicle(Vehicle vehicle);

    public List<Vehicle> getAvailableVehicles();

    public Vehicle findById(Long id);

}
