package model;

import java.time.LocalDate;

public abstract class Person {
    protected String name ;
    protected LocalDate dob ;
    protected String sex ;
    protected String cmnd ;
    protected String phoneNum;
    protected String email ;

    public Person(String name, LocalDate dob, String sex, String cmnd, String phoneNum, String email) {
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.cmnd = cmnd;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
