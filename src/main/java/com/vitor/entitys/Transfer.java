package com.vitor.entitys;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vitor.entitys.dto.TransferDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Table(name = "transfers")
@Entity
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    @Setter
    private BigDecimal calculatedFee;
    private LocalDate createdAt;
    private LocalDate schedulingDate;

    public Transfer(TransferDTO dto) {
        this.sourceAccount = dto.getSourceAccount();
        this.destinationAccount = dto.getDestinationAccount();
        this.calculatedFee = BigDecimal.ZERO;
        this.amount = dto.getAmount();
        this.createdAt = LocalDate.now();
        this.schedulingDate = dto.getSchedulingDate();
    }
}
