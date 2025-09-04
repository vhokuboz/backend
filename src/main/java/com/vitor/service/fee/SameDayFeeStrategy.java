package com.vitor.service.fee;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class SameDayFeeStrategy implements FeeStrategy {

    @Override
    public BigDecimal calculateFee(BigDecimal transferAmount) {
        BigDecimal fixedFee = new BigDecimal("3.00");
        BigDecimal percentageFee = transferAmount.multiply(new BigDecimal("0.025"));
        return fixedFee.add(percentageFee).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean isApplicable(long daysDifference) {
        return daysDifference == 0;
    }

}
