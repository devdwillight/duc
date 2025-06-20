package repository;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public interface IEmployeeRepository extends Repository<Employee, ArrayList<Employee>> {
    @Override
    void writeToFile(ArrayList<Employee> employees);

    @Override
    ArrayList<Employee> readFromFile();
}
