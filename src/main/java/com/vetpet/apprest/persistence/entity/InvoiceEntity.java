package com.vetpet.apprest.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "cost", precision = 6, scale = 2)
    private BigDecimal cost;

    @Column(name = "discount", precision = 2, scale = 1)
    private BigDecimal discount;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointmentEntity;

    @Column(name = "status")
    private Boolean status;

}