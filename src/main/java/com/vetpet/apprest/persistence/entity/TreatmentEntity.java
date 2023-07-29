package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


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

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity petEntity;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

}