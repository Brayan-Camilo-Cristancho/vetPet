package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "appointment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class AppointmentEntity extends AuditableEntity {
    @Id
    @Column(name = "appointment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer duration;

    @Column(length = 500)
    private String description;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity petEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment_type_id", nullable = false)
    private AppointmentTypeEntity appointmentTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctorEntity;

    @OneToOne(mappedBy = "appointmentEntity", cascade = CascadeType.ALL)
    private InvoiceEntity invoiceEntity;

}