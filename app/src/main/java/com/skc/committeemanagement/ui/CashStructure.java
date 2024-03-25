package com.skc.committeemanagement.ui;

public class CashStructure {
    String base, cash, positive, nigetive;

    public CashStructure() {
    }

    public CashStructure(String base, String cash, String positive, String nigetive) {
        this.base = base;
        this.cash = cash;
        this.positive = positive;
        this.nigetive = nigetive;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNigetive() {
        return nigetive;
    }

    public void setNigetive(String nigetive) {
        this.nigetive = nigetive;
    }
}
