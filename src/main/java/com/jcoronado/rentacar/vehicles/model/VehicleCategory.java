package com.jcoronado.rentacar.vehicles.model;

import com.jcoronado.rentacar.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicle_categories")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private BigDecimal dailyPrice;

}
