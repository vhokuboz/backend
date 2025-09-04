package com.vitor.entitys;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    @Pattern(regexp = "\\d{10}")
    @Column(name = "source_account", nullable = false, length = 10)
    private String sourceAccount;

    @NotBlank()
    @Pattern(regexp = "\\d{10}")
    @Column(name = "destination_account", nullable = false, length = 10)
    private String destinationAccount;

    @NotNull()
    @DecimalMin(value = "0.01")
    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @NotNull()
    @DecimalMin(value = "0.00")
    @Column(name = "fee", nullable = false, precision = 19, scale = 2)
    private BigDecimal fee;

    @NotNull()
    @FutureOrPresent()
    @Column(name = "created_at", nullable = false)
    private LocalDate transferDate;

    @Column(name = "scheduling_date", nullable = false)
    private LocalDateTime schedulingDate;

    public Transfer() {
        this.schedulingDate = LocalDateTime.now();
    }

    public Transfer(String sourceAccount, String destinationAccount, BigDecimal amount, BigDecimal fee,
            LocalDate transferDate) {
        this();
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.fee = fee;
        this.transferDate = transferDate;
    }

}
