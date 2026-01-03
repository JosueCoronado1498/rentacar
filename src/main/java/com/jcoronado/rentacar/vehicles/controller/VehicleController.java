package com.jcoronado.rentacar.vehicles.controller;

import com.jcoronado.rentacar.vehicles.dto.VehicleRequest;
import com.jcoronado.rentacar.vehicles.dto.VehicleResponse;
import com.jcoronado.rentacar.vehicles.model.Vehicle;
import com.jcoronado.rentacar.vehicles.model.VehicleCategory;
import com.jcoronado.rentacar.vehicles.repository.IVehicleCategoryRepository;
import com.jcoronado.rentacar.vehicles.services.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private IVehicleCategoryRepository categoryRepository;


    //create vehicle
    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@Valid @RequestBody VehicleRequest request){
        VehicleCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(request.getPlate());
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setCategory(category);

        Vehicle saved = vehicleService.createVehicle(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new VehicleResponse(
                        saved.getId(),
                        saved.getPlate(),
                        saved.getBrand(),
                        saved.getModel(),
                        saved.getYear(),
                        saved.getStatus().name()
                ));
    }

    //get availables vehicles
    @GetMapping("/availables")
    public List<VehicleResponse> getAvailableVehicles(){
        return vehicleService.getAvailableVehicles()
                .stream()
                .map(v -> new VehicleResponse(
                        v.getId(),
                        v.getPlate(),
                        v.getBrand(),
                        v.getModel(),
                        v.getYear(),
                        v.getStatus().name()
                ))
                .toList();
    }

}
