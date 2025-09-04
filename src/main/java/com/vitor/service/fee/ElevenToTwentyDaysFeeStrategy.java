package com.vitor.service.fee;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class ElevenToTwentyDaysFeeStrategy implements FeeStrategy {

    @Override
    public BigDecimal calculateFee(BigDecimal transferAmount) {
        return transferAmount.multiply(new BigDecimal("0.082")).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean isApplicable(long daysDifference) {
        return daysDifference >= 11 && daysDifference <= 20;
    }

}
