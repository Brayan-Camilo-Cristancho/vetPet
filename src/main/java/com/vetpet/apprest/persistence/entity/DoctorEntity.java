package com.vetpet.apprest.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
@NoArgsConstructor
public class DoctorEntity {
    @Id
    @Column(name = "doctor_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long doctorId;
    @Column(name = "num_appointments")
    private Integer numAppointments;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "specialty_id")
    private SpecialtyEntity specialtyEntity;

    @OneToMany(mappedBy = "doctorEntity")
    private Set<AppointmentEntity> appointmentEntities = new LinkedHashSet<>();

}