package com.vetpet.apprest.persistence.entity;


import com.vetpet.apprest.persistence.audit.AuditUserLoginListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "user_login")
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class, AuditUserLoginListener.class})
@NoArgsConstructor
@ToString
public class UserLoginEntity implements Serializable {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;

    @Column(name = "user_id", nullable = false, unique = true)
    Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true, insertable = false, updatable = false)
    private UserEntity userEntity;
}
