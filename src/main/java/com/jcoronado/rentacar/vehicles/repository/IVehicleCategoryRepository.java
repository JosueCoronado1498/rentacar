package com.jcoronado.rentacar.vehicles.repository;

import com.jcoronado.rentacar.vehicles.model.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVehicleCategoryRepository extends JpaRepository<VehicleCategory, Long> {

    Optional<VehicleCategory> findByCategoryName(String categoryName);

    boolean existsByCategoryNameIgnoreCase(String name);

}
