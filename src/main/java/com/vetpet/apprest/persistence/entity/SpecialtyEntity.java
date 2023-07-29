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
@Table(name = "specialty")
@NoArgsConstructor
public class SpecialtyEntity {
    @Id
    @Column(name = "specialty_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long specialtyId;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

    @OneToMany(mappedBy = "specialtyEntity", fetch = FetchType.LAZY)
    private Set<DoctorEntity> doctorEntities = new LinkedHashSet<>();

}