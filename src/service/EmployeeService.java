package service;

import enums.Experience_levels;
import enums.Valid_Positions;
import model.Employee;
import model.Person;
import repository.EmployeeRepository;
import utils.CustomException;
import utils.InputvalidationException;
import view.Validation;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final Validation validation = new Validation();
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Hiển thị danh sách tất cả nhân viên.
     */
    public void displayAllEmployees() {

    }



    // TestCase
    public String createEmployee(String employeeID, String name, String birthday, String gender,
                                 String cmnd, String phoneNum, String email,
                                 String experience, String position, String salary) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required";
        }
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return "Invalid email";
        }
        if (phoneNum == null || !phoneNum.matches("^0\\d{9}$")) {
            return "Invalid phone number";
        }
        if (experience == null || experience.trim().isEmpty()) {
            return "Employee experience is required";
        }

        return "Create success";
    }

    @Override
    public Employee findById(String id) {
        return null;
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void add(Employee entity) {

    }

    @Override
    public void save() {

    }
}