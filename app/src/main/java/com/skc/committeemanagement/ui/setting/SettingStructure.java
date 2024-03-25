package com.skc.committeemanagement.ui.setting;

public class SettingStructure {
    String payMonthlyAmount, cashDeposit;
    int id;

    public SettingStructure() {
    }

    public SettingStructure(String payMonthlyAmount, String cashDeposit, int id) {
        this.payMonthlyAmount = payMonthlyAmount;
        this.cashDeposit = cashDeposit;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayMonthlyAmount() {
        return payMonthlyAmount;
    }

    public void setPayMonthlyAmount(String payMonthlyAmount) {
        this.payMonthlyAmount = payMonthlyAmount;
    }

    public String getCashDeposit() {
        return cashDeposit;
    }

    public void setCashDeposit(String cashDeposit) {
        this.cashDeposit = cashDeposit;
    }
}
