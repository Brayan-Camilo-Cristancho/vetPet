package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Size(max = 100)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @Column(name = "last_name", length = 100)
    private String lastName;

    @Size(max = 200)
    @Column(name = "address", length = 200)
    private String address;

    @Size(max = 10)
    @NotNull
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Size(max = 100)
    @NotNull
    @Email
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "status")
    private Boolean status;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "ownerEntity")
    private Set<PetEntity> petEntities = new LinkedHashSet<>();

}