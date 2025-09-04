package com.vitor.service.fee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeeCalculationService {
    private final List<FeeStrategy> feeStrategies;

    public BigDecimal calculateFee(BigDecimal transferAmount, LocalDate transferDate) {
        long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), transferDate);

        FeeStrategy applicableStrategy = feeStrategies.stream()
                .filter(strategy -> strategy.isApplicable(daysDifference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Data de transferência fora do prazo permitido. " +
                                "Transferências podem ser agendadas apenas até 50 dias no futuro."));

        return applicableStrategy.calculateFee(transferAmount);
    }

}
