package com.vetpet.apprest.persistence.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vetpet.apprest.persistence.audit.AuditUserListener;
import com.vetpet.apprest.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "user")
@EntityListeners({AuditingEntityListener.class, AuditUserListener.class})
@NoArgsConstructor
@ToString
public class UserEntity extends AuditableEntity implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(length = 200)
    private String address;

    @Column
    @Pattern(regexp = "[FM]")
    private Character sex;

    private LocalDate BirthDate;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String identification;

    @Column(columnDefinition = "TINYINT(1) default 0")
    private Boolean assigned_role;

}
