package com.example.techprom_vol;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private String fullname;
    private String loginEmail;
    private String password;
    private String sex;
    private String age;
    private String phone;

    private StringProperty t_fullName;
    private StringProperty t_age;
    private StringProperty t_email;
    private StringProperty t_phone;

    public User(String fullName, String age, String email, String phone) {
        this.t_fullName = new SimpleStringProperty(fullName);
        this.t_age = new SimpleStringProperty(age);
        this.t_email = new SimpleStringProperty(email);
        this.t_phone = new SimpleStringProperty(phone);
    }

    public String getT_fullName() {
        return t_fullName.get();
    }

    public StringProperty t_fullNameProperty() {
        return t_fullName;
    }

    public void setT_fullName(String t_fullName) {
        this.t_fullName.set(t_fullName);
    }

    public String getT_age() {
        return t_age.get();
    }

    public StringProperty t_ageProperty() {
        return t_age;
    }

    public void setT_age(String t_age) {
        this.t_age.set(t_age);
    }

    public String getT_email() {
        return t_email.get();
    }

    public StringProperty t_emailProperty() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email.set(t_email);
    }

    public String getT_phone() {
        return t_phone.get();
    }

    public StringProperty t_phoneProperty() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone.set(t_phone);
    }

    public User() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
