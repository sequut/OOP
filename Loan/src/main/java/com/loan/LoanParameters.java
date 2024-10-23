package com.loan;

import java.time.LocalDate;

public record LoanParameters(double loanAmount, double interestRate, int loanTerm, LocalDate startDate) {
}

