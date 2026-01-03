package com.jcoronado.rentacar.vehicles.controller;

import com.jcoronado.rentacar.vehicles.dto.VehicleCategoryRequest;
import com.jcoronado.rentacar.vehicles.dto.VehicleCategoryResponse;
import com.jcoronado.rentacar.vehicles.dto.VehicleRequest;
import com.jcoronado.rentacar.vehicles.model.VehicleCategory;
import com.jcoronado.rentacar.vehicles.services.VehicleCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicle-categories")
public class VehicleCategoryController {

    @Autowired
    private VehicleCategoryService vehicleCategoryService;

    @PostMapping
    public ResponseEntity<VehicleCategoryResponse> createVehicleCategory(@Valid @RequestBody VehicleCategoryRequest request){
        VehicleCategory category = new VehicleCategory();
        category.setCategoryName(request.getCategoryName());
        category.setDailyPrice(request.getDailyPrice());


        VehicleCategory saved = vehicleCategoryService.createVehicleCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new VehicleCategoryResponse(
                        saved.getId(),
                        saved.getCategoryName(),
                        saved.getDailyPrice()
                ));
    }



}
