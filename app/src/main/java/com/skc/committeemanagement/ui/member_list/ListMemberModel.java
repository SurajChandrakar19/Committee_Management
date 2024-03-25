package com.skc.committeemanagement.ui.member_list;

import android.os.Parcel;
import android.os.Parcelable;

public class ListMemberModel implements Parcelable {
    String name,type,date,number;
    int id;

    public ListMemberModel(String name, String type, String date, String number) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.number = number;
    }

    protected ListMemberModel(Parcel in) {
        name = in.readString();
        type = in.readString();
        date = in.readString();
        number = in.readString();
    }

    public static final Creator<ListMemberModel> CREATOR = new Creator<ListMemberModel>() {
        @Override
        public ListMemberModel createFromParcel(Parcel in) {
            return new ListMemberModel(in);
        }

        @Override
        public ListMemberModel[] newArray(int size) {
            return new ListMemberModel[size];
        }
    };

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


    public ListMemberModel() {
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
        parcel.writeString(type);
        parcel.writeString(date);
        parcel.writeString(number);
    }
}
