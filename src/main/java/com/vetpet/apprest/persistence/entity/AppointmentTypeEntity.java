package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appointment_type")
@NoArgsConstructor
public class AppointmentTypeEntity {
    @Id
    @Column(name = "appointment_type_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentTypeId;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 500)
    private String description;
    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

}