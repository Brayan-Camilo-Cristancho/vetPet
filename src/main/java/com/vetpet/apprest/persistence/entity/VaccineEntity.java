package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "vaccine")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class VaccineEntity extends AuditableEntity {
    @Id
    @Column(name = "vaccine_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vaccineId;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "status")
    private Boolean status;

}