package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pet_vaccine")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class PetVaccineEntity extends AuditableEntity {
    @Id
    @Column(name = "pet_vaccine_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity petEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaccine_id", nullable = false)
    private VaccineEntity vaccineEntity;

    private Short dose;

    @Column(name = "last_dose_date", columnDefinition = "DATETIME")
    private LocalDateTime lastDoseDate;

    @Column(name = "period_at", length = 10)
    private String periodAt;
}