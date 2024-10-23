package com.loan;

import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

public class PaymentScheduleRow {
    private final SimpleStringProperty paymentDate;
    private final BigDecimal totalPayment;
    private final BigDecimal interestPayment;
    private final BigDecimal principalPayment;
    private final BigDecimal remainingBalance;

    public PaymentScheduleRow(String paymentDate, BigDecimal totalPayment, BigDecimal interestPayment, BigDecimal principalPayment, BigDecimal remainingBalance) {
        this.paymentDate = new SimpleStringProperty(paymentDate);
        this.totalPayment = totalPayment;
        this.interestPayment = interestPayment;
        this.principalPayment =  principalPayment;
        this.remainingBalance = remainingBalance;
    }

    public SimpleStringProperty paymentDateProperty() {
        return paymentDate;
    }

    public BigDecimal totalPaymentProperty() {
        return totalPayment;
    }

    public BigDecimal interestPaymentProperty() {
        return interestPayment;
    }

    public BigDecimal principalPaymentProperty() {
        return principalPayment;
    }

    public BigDecimal remainingBalanceProperty() {
        return remainingBalance;
    }
}
