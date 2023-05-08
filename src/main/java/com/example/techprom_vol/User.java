package com.example.techprom_vol;

public class User {
    private String fullname;
    private String loginEmail;
    private String password;
    private String sex;
    private String age;
    private String phone;

//    public User(String fullname, String loginEmail, String password, String sex, String age, String phone) {
//        this.fullname = fullname;
//        this.loginEmail = loginEmail;
//        this.password = password;
//        this.sex = sex;
//        this.age = age;
//        this.phone = phone;
//    }

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
