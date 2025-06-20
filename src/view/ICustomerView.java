package view;

import model.Customer;

import java.util.ArrayList;

public interface ICustomerView extends  View<Customer> {
    @Override
    void display (ArrayList<Customer> etities);

    @Override
    Customer getDetail();

    String  getCustomerID();
}
