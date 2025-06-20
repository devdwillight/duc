package model;

import java.time.LocalDate;

public class Customer extends Person{
    protected String customerID ;
    protected String typeCus ;
    protected String address ;

    public Customer( String customerID,String name, LocalDate dob, String sex, String cmnd, String phoneNum, String email, String typeCus, String address) {
        super(name, dob, sex, cmnd, phoneNum, email);
        this.customerID = customerID;
        this.typeCus = typeCus;
        this.address = address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTypeCus() {
        return typeCus;
    }

    public void setTypeCus(String typeCus) {
        this.typeCus = typeCus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s %-15s %-15s %-10s %-10s %-15s %-15s %-20s %-30s",
                customerID,
                name,
                dob,
                sex,
                cmnd,
                phoneNum,
                email,
                typeCus,
                address

        );
    }
}
