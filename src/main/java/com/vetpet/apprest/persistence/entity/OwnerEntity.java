package com.vetpet.apprest.persistence.entity;

import com.vetpet.apprest.persistence.audit.AuditOwnerListener;
import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "owner")
@EntityListeners({AuditingEntityListener.class, AuditOwnerListener.class})
@NoArgsConstructor
public class OwnerEntity extends AuditableEntity implements Serializable {
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

    @Size(max = 20)
    @NotNull
    @Column(unique = true, nullable = false)
    private String identification;

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


    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL)
    private Set<PetEntity> petEntities = new LinkedHashSet<>();

}