package repository;

import model.Customer;
import model.Employee;

import java.util.ArrayList;
import java.util.List;

public interface ICustomerRepository extends Repository<Customer, ArrayList<Customer>> {
    @Override
    void writeToFile(ArrayList<Customer> customers);

    @Override
    public ArrayList<Customer> readFromFile();
}
