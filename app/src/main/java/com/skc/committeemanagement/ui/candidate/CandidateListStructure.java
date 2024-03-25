package com.skc.committeemanagement.ui.candidate;

public class CandidateListStructure {
    String name,number;

    public CandidateListStructure(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public CandidateListStructure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
