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

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "duration")
    private Integer duration;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity petEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment_type_id", nullable = false)
    private AppointmentTypeEntity appointmentTypeEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctorEntity;

    @Column(name = "status")
    private Boolean status;

    @OneToOne(mappedBy = "appointmentEntity", cascade = CascadeType.ALL)
    private InvoiceEntity invoiceEntity;

}