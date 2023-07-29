package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
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

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

}