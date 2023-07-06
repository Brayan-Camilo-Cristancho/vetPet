package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Size(max = 100)
    @Column(name = "type", length = 100)
    private String type;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Size(max = 100)
    @Column(name = "supplier", length = 100)
    private String supplier;

    @Column(name = "initial_quantity")
    private Integer initialQuantity;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "status")
    private Boolean status;

}