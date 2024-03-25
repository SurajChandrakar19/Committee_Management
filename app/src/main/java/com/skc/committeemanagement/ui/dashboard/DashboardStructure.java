package com.skc.committeemanagement.ui.dashboard;

import android.util.Patterns;

public class DashboardStructure {
    String cash,payTotal,ProfitTotal;

    public DashboardStructure() {
    }

    public DashboardStructure(String cash, String payTotal, String profitTotal) {
        this.cash = cash;
        this.payTotal = payTotal;
        ProfitTotal = profitTotal;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(String payTotal) {
        this.payTotal = payTotal;
    }

    public String getProfitTotal() {
        return ProfitTotal;
    }

    public void setProfitTotal(String profitTotal) {
        ProfitTotal = profitTotal;
    }

}
