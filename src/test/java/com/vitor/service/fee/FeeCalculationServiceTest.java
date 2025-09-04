package com.vitor.service.fee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeeCalculationServiceTest {
    private FeeCalculationService feeCalculationService;
    private FeeStrategy sameDayStrategy;
    private FeeStrategy tenDaysStrategy;

    @BeforeEach
    void setUp() {
        sameDayStrategy = mock(FeeStrategy.class);
        tenDaysStrategy = mock(FeeStrategy.class);
        List<FeeStrategy> strategies = Arrays.asList(sameDayStrategy, tenDaysStrategy);
        feeCalculationService = new FeeCalculationService(strategies);
    }

    @Test
    void testCalculateFeeWithApplicableStrategy() {
        BigDecimal amount = BigDecimal.valueOf(100);
        LocalDate transferDate = LocalDate.now();
        when(sameDayStrategy.isApplicable(0)).thenReturn(true);
        when(sameDayStrategy.calculateFee(amount)).thenReturn(BigDecimal.valueOf(10));

        BigDecimal fee = feeCalculationService.calculateFee(amount, transferDate);
        assertEquals(BigDecimal.valueOf(10), fee);
        verify(sameDayStrategy).isApplicable(0);
        verify(sameDayStrategy).calculateFee(amount);
    }

    @Test
    void testCalculateFeeThrowsExceptionIfNoStrategy() {
        BigDecimal amount = BigDecimal.valueOf(100);
        LocalDate transferDate = LocalDate.now().plusDays(100);
        when(sameDayStrategy.isApplicable(anyLong())).thenReturn(false);
        when(tenDaysStrategy.isApplicable(anyLong())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            feeCalculationService.calculateFee(amount, transferDate);
        });
    }
}
