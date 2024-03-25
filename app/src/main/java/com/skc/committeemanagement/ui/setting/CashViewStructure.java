package com.skc.committeemanagement.ui.setting;

public class CashViewStructure {
    long cashInHand,nagetiveCash,positiveCash;

    public CashViewStructure() {
    }

    public CashViewStructure(long cashInHand, long nagetiveCash, long positiveCash) {
        this.cashInHand = cashInHand;
        this.nagetiveCash = nagetiveCash;
        this.positiveCash = positiveCash;
    }

    public long getCashInHand() {
        return cashInHand;
    }

    public void setCashInHand(long cashInHand) {
        this.cashInHand = cashInHand;
    }

    public long getNagetiveCash() {
        return nagetiveCash;
    }

    public void setNagetiveCash(long nagetiveCash) {
        this.nagetiveCash = nagetiveCash;
    }

    public long getPositiveCash() {
        return positiveCash;
    }

    public void setPositiveCash(long positiveCash) {
        this.positiveCash = positiveCash;
    }
}
