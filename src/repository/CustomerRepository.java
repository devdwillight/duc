package repository;

import model.Customer;
import model.Employee;
import utils.CustomException;
import view.Validation;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements  ICustomerRepository{
    private static final String FILE_PATH = "/data/customer.txt";

    public CustomerRepository() {
        File file = new File( path+FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Đã tạo file customer.txt mới.");
            } catch (IOException e) {
                System.err.println("Lỗi khi tạo file customer.txt: " + e.getMessage());
            }
        }
    }
    @Override
    public void writeToFile(ArrayList<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter( path + FILE_PATH))) {
            for (Customer customer : customers) {
                bw.write(String.join(",",
                        customer.getCustomerID(),
                        customer.getName(),
                        customer.getDob().format(Validation.getFileDateFormatter()), // Sử dụng DateTimeFormatter từ Validator
                        customer.getSex(),
                        customer.getCmnd(),
                        customer.getPhoneNum(),
                        customer.getEmail(),
                        customer.getTypeCus(),
                        customer.getAddress()
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file customer.txt: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Customer>readFromFile() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader( path+ FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống
                String[] data = line.split(",");
                if (data.length == 9) { // Đảm bảo đủ số lượng cột cho Customer
                    try {
                        String customerID = data[0];
                        String name = data[1];
                        // Sử dụng DateTimeFormatter từ Validator
                        LocalDate dob = LocalDate.parse(data[2], Validation.getFileDateFormatter());
                        String sex = data[3];
                        String cmnd = data[4];
                        String phoneNum = data[5];
                        String email = data[6];
                        String customerType = data[7];
                        String address = data[8];

                        customers.add(new Customer(customerID, name, dob, sex, cmnd, phoneNum, email, customerType, address));
                    } catch (DateTimeParseException | NumberFormatException e) {
                        System.err.println("Lỗi định dạng dữ liệu trong file customer.csv: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Lỗi định dạng dòng trong file customer.csv (số cột không đúng): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file customer.csv: " + e.getMessage());
        }
        return customers;
    }
    public void updateCustomer(Customer updatedCustomer) throws CustomException {
        ArrayList<Customer> customers = readFromFile();
        boolean found = false;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID().equals(updatedCustomer.getCustomerID())) {
                customers.set(i, updatedCustomer);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new CustomException("Không tìm thấy khách hàng với ID: " + updatedCustomer.getCustomerID());
        }
        writeToFile(customers);
    }
    public void addCustomer(Customer customer) {
        ArrayList<Customer> customers = readFromFile();
        customers.add(customer);
        writeToFile(customers);
    }
}
