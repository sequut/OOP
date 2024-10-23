package com.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentSchedule(int paymentNumber, LocalDate paymentDate, BigDecimal totalPayment, BigDecimal interestPayment,
                              BigDecimal principalPayment, BigDecimal remainingBalance) {
}

