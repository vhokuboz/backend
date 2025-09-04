package com.vitor.entitys.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferDTO {

    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private LocalDate createdAt;
    private LocalDate schedulingDate;

    public TransferDTO() {
        this.createdAt = LocalDate.now();
    }

    public TransferDTO(String sourceAccount, String destinationAccount, BigDecimal amount) {
        this();
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }   

}