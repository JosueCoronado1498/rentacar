package com.jcoronado.rentacar.rentals.repository;

import com.jcoronado.rentacar.common.enums.RentalStatus;
import com.jcoronado.rentacar.rentals.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserId(Long userId);

    List<Rental> findByVehicleId(Long vehicleId);

    boolean existsByVehicleIdAndStatusIn(Long vehicleId, List<RentalStatus> statuses);

}
