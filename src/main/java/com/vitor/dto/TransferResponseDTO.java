package com.vitor.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.vitor.entitys.Transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferResponseDTO {
    private Long id;
    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private BigDecimal fee;
    private LocalDate transferDate;
    private LocalDateTime schedulingDate;

    public TransferResponseDTO(Transfer transfer) {
        this.id = transfer.getId();
        this.sourceAccount = transfer.getSourceAccount();
        this.destinationAccount = transfer.getDestinationAccount();
        this.amount = transfer.getAmount();
        this.fee = transfer.getFee();
        this.transferDate = transfer.getTransferDate();
        this.schedulingDate = transfer.getSchedulingDate();
    }

}
