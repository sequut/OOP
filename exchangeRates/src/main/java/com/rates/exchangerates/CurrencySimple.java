package com.rates.exchangerates;

public class CurrencySimple {
    private final String name;
    private final double buy;
    private final double sell;

    public CurrencySimple(String name, double buy, double sell){
        this.name = name;
        this.buy = buy;
        this.sell = sell;
    }

    public String getName(){
        return name;
    }

    public double getBuy() {
        return buy;
    }

    public double getSell() {
        return sell;
    }

    @Override
    public String toString() {
        return name + ":\n\tbuy: " + buy + "\n\tsell: " + sell + "\n";
    }
}
