package com.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculator {
    public List<PaymentSchedule> calculateDifferentiated(LoanParameters params) {
        List<PaymentSchedule> schedule = new ArrayList<>();
        BigDecimal remainingBalance = BigDecimal.valueOf(params.loanAmount()).setScale(2, RoundingMode.UP);
        BigDecimal monthlyPrincipalPayment = BigDecimal.valueOf(params.loanAmount() / params.loanTerm()).setScale(2, RoundingMode.UP);
        BigDecimal monthlyInterestRate = BigDecimal.valueOf(params.interestRate() / 12 / 100).setScale(2, RoundingMode.UP);

        for (int i = 0; i < params.loanTerm(); i++) {
            BigDecimal interestPayment = remainingBalance.multiply(monthlyInterestRate).setScale(2, RoundingMode.UP);
            BigDecimal totalPayment = monthlyPrincipalPayment.add(interestPayment).setScale(2, RoundingMode.UP);
            remainingBalance = remainingBalance.subtract(monthlyPrincipalPayment).setScale(2, RoundingMode.UP);

            if (i == params.loanTerm() - 1)
                remainingBalance = BigDecimal.valueOf(0);

            schedule.add(new PaymentSchedule(
                    i + 1,
                    params.startDate().plusMonths(i),
                    totalPayment.setScale(2, RoundingMode.UP),
                    interestPayment.setScale(2, RoundingMode.UP),
                    monthlyPrincipalPayment.setScale(2, RoundingMode.UP),
                    remainingBalance.setScale(2, RoundingMode.UP)
            ));
        }
        return schedule;
    }

    public List<PaymentSchedule> calculateAnnuity(LoanParameters params) {
        List<PaymentSchedule> schedule = new ArrayList<>();
        BigDecimal monthlyInterestRate = BigDecimal.valueOf(params.interestRate() / 12 / 100)
                .setScale(2, RoundingMode.UP);
        BigDecimal annuityPayment = BigDecimal.valueOf(params.loanAmount() * (monthlyInterestRate.doubleValue() /
                (1 - Math.pow(1 + monthlyInterestRate.doubleValue(), -params.loanTerm()))))
                .setScale(2, RoundingMode.UP);
        BigDecimal remainingBalance = BigDecimal.valueOf(params.loanAmount()).setScale(2, RoundingMode.UP);

        for (int i = 0; i < params.loanTerm(); i++) {
            BigDecimal interestPayment = remainingBalance.multiply(monthlyInterestRate).setScale(2, RoundingMode.UP);
            BigDecimal principalPayment = annuityPayment.subtract(interestPayment).setScale(2, RoundingMode.UP);
            remainingBalance = remainingBalance.subtract(principalPayment).setScale(2, RoundingMode.UP);

            if (i == params.loanTerm() - 1)
                remainingBalance = BigDecimal.valueOf(0);

            schedule.add(new PaymentSchedule(
                    i + 1,
                    params.startDate().plusMonths(i),
                    annuityPayment.setScale(2, RoundingMode.UP),
                    interestPayment.setScale(2, RoundingMode.UP),
                    principalPayment.setScale(2, RoundingMode.UP),
                    remainingBalance.setScale(2, RoundingMode.UP)
            ));
        }
        return schedule;
    }
}
