package view;

import model.Customer;
import model.Employee;

import java.util.ArrayList;

public interface IEmployeeView extends View<Employee> {
    @Override
    void display (ArrayList<Employee> etities);

    @Override
    Employee getDetail();

    String  getEmployeeID();
}
