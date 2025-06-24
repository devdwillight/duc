package service;

import model.Customer;
import model.Employee;
import utils.CustomException;

public interface IEmployeeService extends Service<Employee> {
    @Override
    void add (Employee entity) throws CustomException;

    @Override
    void save();

    @Override
    Employee findById(String id);

    void update(Employee entity);
}
