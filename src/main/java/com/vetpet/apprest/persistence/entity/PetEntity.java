package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditPetListener;
import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pet")
@EntityListeners({AuditingEntityListener.class, AuditPetListener.class})
@NoArgsConstructor
public class PetEntity extends AuditableEntity implements Serializable {
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
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
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

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppointmentEntity> appointmentEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PetVaccine> petVaccines = new LinkedHashSet<>();

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TreatmentEntity> treatmentEntities = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "PetEntity{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", hasChip=" + hasChip +
                ", birthDate=" + birthDate +
                ", ownerEntity=" + ownerEntity +
                ", ownerId=" + ownerId +
                ", status=" + status +
                ", idChip='" + idChip + '\'' +
                '}';
    }
}