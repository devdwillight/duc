package service;

import model.Customer;
import model.Employee;

public interface ICustomerService extends Service<Customer> {
    @Override
    void add (Customer entity);

    @Override
    void save();

    @Override
    Customer findById(String id);

    void update(Customer entity);
}
