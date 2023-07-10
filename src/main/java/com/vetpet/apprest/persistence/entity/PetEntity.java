package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pet")
@NoArgsConstructor
public class PetEntity {
    @Id
    @Column(name = "pet_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petId;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "species", nullable = false, length = 50)
    private String species;

    @Size(max = 50)
    @NotNull
    @Column(name = "breed", nullable = false, length = 50)
    private String breed;

    @Column(name = "has_chip")
    private Boolean hasChip;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false, insertable = false, updatable = false)
    private OwnerEntity ownerEntity;

    @Column(name = "owner_id")
    @NotNull
    private Long ownerId;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "chip_id", unique = true, nullable = false)
    @NotNull
    @Size(max = 20)
    private String idChip;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppointmentEntity> appointmentEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PetVaccine> petVaccines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TreatmentEntity> treatmentEntities = new LinkedHashSet<>();

}