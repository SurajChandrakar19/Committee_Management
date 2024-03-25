package com.skc.committeemanagement.ui.manage_pay;

import android.os.Parcel;
import android.os.Parcelable;

public class ManagePayStructure implements Parcelable {
    private String name,address,amount,rate,number, date;
    int id;

    public ManagePayStructure() {
    }

    public ManagePayStructure(int id, String name, String address, String amount, String rate, String number, String date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.rate = rate;
        this.number = number;
        this.date = date;
    }

    protected ManagePayStructure(Parcel in) {
        name = in.readString();
        address = in.readString();
        amount = in.readString();
        rate = in.readString();
        number = in.readString();
        date = in.readString();
        id = in.readInt();
    }

    public static final Creator<ManagePayStructure> CREATOR = new Creator<ManagePayStructure>() {
        @Override
        public ManagePayStructure createFromParcel(Parcel in) {
            return new ManagePayStructure(in);
        }

        @Override
        public ManagePayStructure[] newArray(int size) {
            return new ManagePayStructure[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
//        if (name.equals(""))
//        {
//            this.name = "";
//        }else {
            this.name = name;
//        }

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(amount);
        parcel.writeString(rate);
        parcel.writeString(number);
        parcel.writeString(date);
        parcel.writeInt(id);
    }
//
//    public boolean ifBlank(){
//        if (name.equals("")){
//            return false;
//        }
//        return tre
//    }


}
