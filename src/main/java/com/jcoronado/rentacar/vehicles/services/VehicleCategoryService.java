package com.jcoronado.rentacar.vehicles.services;

import com.jcoronado.rentacar.common.enums.VehicleStatus;
import com.jcoronado.rentacar.vehicles.model.VehicleCategory;
import com.jcoronado.rentacar.vehicles.repository.IVehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleCategoryService implements IVehicleCategoryService{

    @Autowired
    private IVehicleCategoryRepository vehicleCategoryRepository;


    @Override
    public VehicleCategory createVehicleCategory(VehicleCategory vehicleCategory) {
        String normalizedName = vehicleCategory.getCategoryName().trim().toUpperCase();

        if (vehicleCategoryRepository.existsByCategoryNameIgnoreCase(normalizedName)){
            throw new IllegalArgumentException("Category already exists");
        }

        vehicleCategory.setCategoryName(normalizedName);

        return vehicleCategoryRepository.save(vehicleCategory);
    }
}