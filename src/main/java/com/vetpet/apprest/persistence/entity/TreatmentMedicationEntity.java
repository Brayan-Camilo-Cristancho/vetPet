package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "treatment_medication")
public class TreatmentMedicationEntity {
    @Id
    @Column(name = "treatment_medication_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medication_id", nullable = false)
    private MedicationEntity medicationEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treatment_id", nullable = false)
    private TreatmentEntity treatmentEntity;

    @Column(name = "dose")
    private Integer dose;

    @Size(max = 50)
    @Column(name = "frequency", length = 50)
    private String frequency;

    @Size(max = 50)
    @Column(name = "period_at", length = 50)
    private String periodAt;

}