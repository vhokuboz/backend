package com.vitor.service.fee;

import java.math.BigDecimal;

public interface FeeStrategy {
    BigDecimal calculateFee(BigDecimal transferAmount);

    boolean isApplicable(long daysDifference);
}
