package com.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculator {
    public List<PaymentSchedule> calculateDifferentiated(LoanParameters params) {
        List<PaymentSchedule> schedule = new ArrayList<>();
        double remainingBalance = params.loanAmount();
        double monthlyPrincipalPayment = params.loanAmount() / params.loanTerm();
        double monthlyInterestRate = params.interestRate() / 12 / 100;

        for (int i = 0; i < params.loanTerm(); i++) {
            double interestPayment = remainingBalance * monthlyInterestRate;
            double totalPayment = monthlyPrincipalPayment + interestPayment;
            remainingBalance -= monthlyPrincipalPayment;

            schedule.add(new PaymentSchedule(
                    i + 1,
                    params.startDate().plusMonths(i),
                    BigDecimal.valueOf(totalPayment).setScale(2, RoundingMode.UP),
                    BigDecimal.valueOf(interestPayment).setScale(2, RoundingMode.UP),
                    BigDecimal.valueOf(monthlyPrincipalPayment).setScale(2, RoundingMode.UP),
                    BigDecimal.valueOf(remainingBalance).setScale(2, RoundingMode.UP)
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
            BigDecimal interestPayment = remainingBalance.multiply(monthlyInterestRate);
            BigDecimal principalPayment = annuityPayment.subtract(interestPayment);
            remainingBalance = remainingBalance.subtract(principalPayment);

            schedule.add(new PaymentSchedule(
                    i + 1,
                    params.startDate().plusMonths(i),
                    annuityPayment,
                    interestPayment,
                    principalPayment,
                    remainingBalance
            ));
        }
        return schedule;
    }
}
