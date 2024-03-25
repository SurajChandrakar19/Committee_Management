package com.skc.committeemanagement.login_tab;

public class SignupDataHolder {

    String name , address , mobile_no, password;

    public SignupDataHolder(String address, String mobile_no, String name, String password) {
        this.name = name;
        this.address = address;
        this.mobile_no = mobile_no;
        this.password = password;
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
        address = address;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
