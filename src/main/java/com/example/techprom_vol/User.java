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

    private StringProperty fs_fullName;
    private StringProperty fs_age;
    private StringProperty fs_email;
    private StringProperty fs_phone;

    private StringProperty ss_fullName;
    private StringProperty ss_age;
    private StringProperty ss_email;
    private StringProperty ss_phone;

    public User(String fullName, String age, String email, String phone) {
        this.t_fullName = new SimpleStringProperty(fullName);
        this.t_age = new SimpleStringProperty(age);
        this.t_email = new SimpleStringProperty(email);
        this.t_phone = new SimpleStringProperty(phone);

        this.fs_fullName = new SimpleStringProperty(fullName);
        this.fs_age = new SimpleStringProperty(age);
        this.fs_email = new SimpleStringProperty(email);
        this.fs_phone = new SimpleStringProperty(phone);

        this.ss_fullName = new SimpleStringProperty(fullName);
        this.ss_age = new SimpleStringProperty(age);
        this.ss_email = new SimpleStringProperty(email);
        this.ss_phone = new SimpleStringProperty(phone);
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

    public String getFs_fullName() {
        return fs_fullName.get();
    }

    public StringProperty fs_fullNameProperty() {
        return fs_fullName;
    }

    public void setFs_fullName(String fs_fullName) {
        this.fs_fullName.set(fs_fullName);
    }

    public String getFs_age() {
        return fs_age.get();
    }

    public StringProperty fs_ageProperty() {
        return fs_age;
    }

    public void setFs_age(String fs_age) {
        this.fs_age.set(fs_age);
    }

    public String getFs_email() {
        return fs_email.get();
    }

    public StringProperty fs_emailProperty() {
        return fs_email;
    }

    public void setFs_email(String fs_email) {
        this.fs_email.set(fs_email);
    }

    public String getFs_phone() {
        return fs_phone.get();
    }

    public StringProperty fs_phoneProperty() {
        return fs_phone;
    }

    public void setFs_phone(String fs_phone) {
        this.fs_phone.set(fs_phone);
    }

    public String getSs_fullName() {
        return ss_fullName.get();
    }

    public StringProperty ss_fullNameProperty() {
        return ss_fullName;
    }

    public void setSs_fullName(String ss_fullName) {
        this.ss_fullName.set(ss_fullName);
    }

    public String getSs_age() {
        return ss_age.get();
    }

    public StringProperty ss_ageProperty() {
        return ss_age;
    }

    public void setSs_age(String ss_age) {
        this.ss_age.set(ss_age);
    }

    public String getSs_email() {
        return ss_email.get();
    }

    public StringProperty ss_emailProperty() {
        return ss_email;
    }

    public void setSs_email(String ss_email) {
        this.ss_email.set(ss_email);
    }

    public String getSs_phone() {
        return ss_phone.get();
    }

    public StringProperty ss_phoneProperty() {
        return ss_phone;
    }

    public void setSs_phone(String ss_phone) {
        this.ss_phone.set(ss_phone);
    }
}
