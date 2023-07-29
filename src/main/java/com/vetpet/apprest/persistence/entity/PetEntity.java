package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
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
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
public class PetEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "pet_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long petId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String species;

    @Column(nullable = false, length = 50)
    private String breed;

    @Column(name = "has_chip", columnDefinition = "TINYINT(1) default 1")
    private Boolean hasChip;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

    @Column(name = "chip_id", unique = true, nullable = false, length = 20)
    private String idChip;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "owner_id", nullable = false, insertable = false, updatable = false)
    private OwnerEntity ownerEntity;

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppointmentEntity> appointmentEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "petEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PetVaccineEntity> petVaccineEntities = new LinkedHashSet<>();

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