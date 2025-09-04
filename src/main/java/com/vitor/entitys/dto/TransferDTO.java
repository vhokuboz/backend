package com.vitor.entitys.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransferDTO {

    @NotBlank(message = "Conta de origem é obrigatória")
    @Pattern(regexp = "^\\d{10}$", message = "Conta de origem deve ter exatamente 10 dígitos")
    private String sourceAccount;

    @NotBlank(message = "Conta de origem é obrigatória")
    @Pattern(regexp = "^\\d{10}$", message = "Conta de destino deve ter exatamente 10 dígitos")
    private String destinationAccount;

    @NotNull(message = "Valor da transferência é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal amount;

    @NotNull(message = "Data de transferência é obrigatória")
    @Future(message = "Data de transferência deve ser futura")
    private LocalDate transferDate;

}