package com.jcoronado.rentacar.rentals.model;

import com.jcoronado.rentacar.common.enums.RentalStatus;
import com.jcoronado.rentacar.users.model.User;
import com.jcoronado.rentacar.vehicles.model.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Quien alquila
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    //Que auto
    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    //Fechas
    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    //Estado del Alquiler
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentalStatus status;

    //Precio final
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private boolean active = true;



}
