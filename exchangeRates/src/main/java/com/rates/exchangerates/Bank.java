package com.rates.exchangerates;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final String name;
    private final List<CurrencySimple> currencies;

    public Bank(String name){
        this.name = name;
        this.currencies = new ArrayList<>();
    }

    public void addCurrency(CurrencySimple currency){
        currencies.add(currency);
    }

    public String getName() {
        return name;
    }

    public List<CurrencySimple> getCurrencies() {
        return currencies;
    }

    public double getCurrencyRate(String currencyName, boolean isBuyRate) {
        for (CurrencySimple currency : currencies)
            if (currency.getName().equals(currencyName))
                return isBuyRate ? currency.getBuy() : currency.getSell();
        return 0.0;
    }

    @Override
    public String toString() {
        return name + "\n" + getCurrencies().toString();
    }
}
