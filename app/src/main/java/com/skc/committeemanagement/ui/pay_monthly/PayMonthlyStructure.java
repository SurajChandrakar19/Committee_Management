package com.skc.committeemanagement.ui.pay_monthly;

public class PayMonthlyStructure {
    String name;
    String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PayMonthlyStructure() {
    }

    public PayMonthlyStructure(String namer,String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
