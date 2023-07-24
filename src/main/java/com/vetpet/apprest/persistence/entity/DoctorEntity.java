package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class DoctorEntity extends AuditableEntity {
    @Id
    @Column(name = "doctor_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long doctorId;

    @Size(max = 100)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @Column(name = "last_name", length = 100)
    private String lastName;

    @Size(max = 10)
    @Column(name = "phone", length = 10)
    private String phone;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    @Email
    private String email;
    @Column
    @Size(max = 100)
    @Positive
    private Integer age;
    @Column
    @Pattern(regexp = "[FM]")
    private Character sex;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "specialty_id")
    private SpecialtyEntity specialtyEntity;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "doctorEntity")
    private Set<AppointmentEntity> appointmentEntities = new LinkedHashSet<>();

}