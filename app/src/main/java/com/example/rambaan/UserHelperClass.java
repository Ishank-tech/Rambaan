package com.example.rambaan;

public class UserHelperClass {

    String fullName, username, email, phoneNo, password, dob, gender;

    public UserHelperClass(){}

    public UserHelperClass(String fullName, String username, String email, String phoneNo, String password, String dob, String gender) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
