package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pet_vaccine")
@NoArgsConstructor
public class PetVaccine {
    @Id
    @Column(name = "pet_vaccine_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity petEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaccine_id", nullable = false)
    private VaccineEntity vaccineEntity;

    @Column(name = "dose")
    private Short dose;

    @Column(name = "last_dose_date", columnDefinition = "DATETIME")
    private LocalDateTime lastDoseDate;

    @Size(max = 10)
    @Column(name = "period_at", length = 10)
    private String periodAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}