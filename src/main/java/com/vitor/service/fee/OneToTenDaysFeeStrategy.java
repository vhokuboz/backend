package com.vitor.service.fee;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class OneToTenDaysFeeStrategy implements FeeStrategy {

    @Override
    public BigDecimal calculateFee(BigDecimal transferAmount) {
        return new BigDecimal("12.00");
    }

    @Override
    public boolean isApplicable(long daysDifference) {
        return daysDifference >= 1 && daysDifference <= 10;
    }

}
