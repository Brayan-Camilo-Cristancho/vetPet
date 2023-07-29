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
@Table(name = "owner")
@NoArgsConstructor
public class OwnerEntity {
    @Id
    @Column(name = "owner_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerId;

    @Column(name = "total_Spent")
    private Double totalSpent;

    private Boolean membership;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL)
    private Set<PetEntity> petEntities = new LinkedHashSet<>();
}
