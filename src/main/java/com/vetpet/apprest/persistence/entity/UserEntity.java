package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private OwnerEntity ownerEntity;
}