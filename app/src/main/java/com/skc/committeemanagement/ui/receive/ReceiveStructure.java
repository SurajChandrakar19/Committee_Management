package com.skc.committeemanagement.ui.receive;

import android.os.Parcel;
import android.os.Parcelable;

public class ReceiveStructure implements  Parcelable {
    String name,address,principal_amount,interest,total_amount,rate,number, date,receiveDate;
    int id;

    public ReceiveStructure(String name, String address, String rate, String number, String date, String principal_amount, String interest, String total_amount, int id , String receiveDate) {
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.number = number;
        this.date = date;
        this.principal_amount = principal_amount;
        this.interest = interest;
        this.total_amount = total_amount;
        this.id = id;
        this.receiveDate = receiveDate;
    }

    protected ReceiveStructure(Parcel in) {
        name = in.readString();
        address = in.readString();
        principal_amount = in.readString();
        rate = in.readString();
        number = in.readString();
        date = in.readString();
        id = in.readInt();
    }

    public static final Parcelable.Creator<ReceiveStructure> CREATOR = new Parcelable.Creator<ReceiveStructure>() {
        @Override
        public ReceiveStructure createFromParcel(Parcel in) {
            return new ReceiveStructure(in);
        }

        @Override
        public ReceiveStructure[] newArray(int size) {
            return new ReceiveStructure[size];
        }
    };

    public ReceiveStructure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrincipal_amount() {
        return principal_amount;
    }

    public void setPrincipal_amount(String principal_amount) {
        this.principal_amount = principal_amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(principal_amount);
        parcel.writeString(rate);
        parcel.writeString(number);
        parcel.writeString(date);
        parcel.writeInt(id);
    }
}
