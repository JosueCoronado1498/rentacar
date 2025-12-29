package com.jcoronado.rentacar.vehicles.repository;

import com.jcoronado.rentacar.common.enums.VehicleStatus;
import com.jcoronado.rentacar.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatus(VehicleStatus status);

    boolean existsByPlate(String plate);

}
