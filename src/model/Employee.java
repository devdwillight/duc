package model;

import java.time.LocalDate;

public class Employee  extends  Person{
    protected String employeeID ;
    protected String experience;
    protected String position;
    protected double salary ;

    public Employee(String employeeID,String name, LocalDate dob, String sex, String cmnd, String phoneNum, String email, String experience, String position, double salary) {
        super(name, dob, sex, cmnd, phoneNum, email);
        this.employeeID = employeeID;
        this.experience = experience;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s %-15s %-15s %-10s %-10s %-15s %-15s %-20s %-30s %-10.2f",
                employeeID,
                name,
                dob,
                sex,
                cmnd,
                phoneNum,
                email,
                experience,
                position,
                salary
        );
    }
}
