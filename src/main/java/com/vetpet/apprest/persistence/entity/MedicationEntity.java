package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medication")
@NoArgsConstructor
public class MedicationEntity {
    @Id
    @Column(name = "medication_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medicationId;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 100)
    private String type;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String supplier;

    private Integer initialQuantity;

    private Integer stock;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

}