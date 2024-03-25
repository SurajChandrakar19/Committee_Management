package com.skc.committeemanagement.ui.add_member;

import java.util.Objects;

public class AddStructure {
    String name, type, date, number;

    public AddStructure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AddStructure(String name, String type, String date, String number) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.number = number;
    }
}
