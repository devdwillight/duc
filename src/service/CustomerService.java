package service;

import enums.Customer_types;
import enums.Gender_options;
import enums.Valid_Positions;
import model.Customer;
import repository.CustomerRepository;
import utils.CustomException;
import utils.Utils;
import view.Validation;
import view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER = Validation.getDisplayDateFormatter(); // Lấy từ Validator


    public void addNewCustomer(Customer entity) {
    customerRepository.addCustomer(entity);


    }

    public ArrayList<Customer> findAll() {
        ArrayList<Customer> customers = customerRepository.readFromFile();
        return customers;
    }


    public static String createCustomer(int id, String name, String birthday, String gender,
                                        String phone, String email, String address, String type) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required";
        }
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return "Invalid email";
        }
        if (phone == null || !phone.matches("^0\\d{9}$")) {
            return "Invalid phone number";
        }
        if (type == null || type.trim().isEmpty()) {
            return "Customer type is required";
        }
        // Nếu hợp lệ, có thể lưu vào list hoặc file
        return "Create success";
    }

    @Override
    public Customer findById(String id) {
        List<Customer> customers = customerRepository.readFromFile();
        Customer customer = null;
        for (Customer cust : customers) {
            if (cust.getCustomerID().equals(id)) {
                customer = cust;
                break;
            }
        }
        return customer;
    }

    @Override
    public void update(Customer entity) {
        try {
            customerRepository.updateCustomer(entity);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Customer entity) {
    customerRepository.addCustomer(entity);
    }

    @Override
    public void save() {
    }
}