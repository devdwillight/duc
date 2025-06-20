package service;

import model.Customer;
import model.Employee;

public interface IEmployeeService extends Service<Employee> {
    @Override
    void add (Employee entity);

    @Override
    void save();

    @Override
    Employee findById(String id);

    void update(Employee entity);
}
