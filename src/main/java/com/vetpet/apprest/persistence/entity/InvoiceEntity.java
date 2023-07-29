package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "invoice")
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    @Column(name = "invoice_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;
    @Column(length = 500)
    private String description;

    private LocalDate date;

    @Column(precision = 6, scale = 2)
    private BigDecimal cost;

    @Column(precision = 5, scale = 2)
    private BigDecimal discount;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointmentEntity;
    @Column(columnDefinition = "TINYINT(1) default 1")
    private Boolean status;

}