package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "treatment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class TreatmentEntity extends AuditableEntity {
    @Id
    @Column(name = "treatment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long treatmentId;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity petEntity;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "treatmentEntity", cascade = CascadeType.ALL)
    private Set<TreatmentMedicationEntity> treatmentMedicationEntities = new LinkedHashSet<>();

}