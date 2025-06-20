package view;

import jdk.jshell.execution.Util;
import model.Customer;
import service.CustomerService;
import utils.Utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CustomerView implements ICustomerView {
    CustomerService customerService = new CustomerService();

    @Override
    public void display(ArrayList<Customer> entities) {
        if (entities == null || entities.isEmpty()) {
            System.out.println("Không có khách hàng nào để hiển thị.");
            return;
        }

        printSeparator('+', 12, 20, 12, 10, 15, 25, 10, 15, 25);
        System.out.printf("| %-10s | %-18s | %-10s | %-8s | %-13s | %-23s | %-8s | %-13s | %-23s |\n",
                "Mã KH", "Tên KH", "Ngày sinh", "Giới tính", "CMND", "Email", "SĐT", "Loại", "Địa chỉ");
        printSeparator('+', 12, 20, 12, 10, 15, 25, 10, 15, 25);

        for (Customer c : entities) {
            System.out.printf("| %-10s | %-18s | %-10s | %-8s | %-13s | %-23s | %-8s | %-13s | %-23s |\n",
                    c.getCustomerID(), c.getName(), c.getDob().format(Validation.DISPLAY_DATE_FORMATTER), c.getSex(),
                    c.getCmnd(), c.getEmail(), c.getPhoneNum(), c.getTypeCus(), c.getAddress());
        }

        printSeparator('+', 12, 20, 12, 10, 15, 25, 10, 15, 25);
    }

    @Override
    public Customer getDetail() {
        String gender;
        LocalDate birth;
        String IDNumber = Utils.getAStringFormat("Input ID Number : ", "ID card must have 9 or 12 digits", "^(\\d{9}|\\d{11})$");
        String fullName = Utils.getAString("Input name of customer : ", "InvalidString");
        birth = Utils.getALocalDate("Input date of birth: ", "Invalid input");
        gender = Utils.getAGender("What is your gender (Nam | Nu | Khac  ): ", "Invalid input!");
        String phone = Utils.getAStringFormat("Input phone Number: ", "Phone number must have 10 numbers", "^0\\d{9}$");
        String email = Utils.getAStringFormat("Input email: ", "Invalid emial!", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        String cusID = checkCusID();
        String typeCustomer = Validation.getValidCustomerType("Input Customer Type", "Error: Invalid input. Please enter one of the : ");
        String address = Utils.getAString("Input Address", "Invalid input");
        return new Customer(cusID, fullName, birth, gender, IDNumber, phone, email, typeCustomer, address);
    }

    public String checkCusID() {
        String cusID;
        while (true) {
            cusID = Utils.getAStringFormat("Input CustomerID: ", "Customer ID must be in the correct form KH-0000", "^KH-\\d{4}$");
            if (customerService.findById(cusID) == null) {
                break;
            } else {
                System.out.println("This customer ID  already exits, please input a new ID");
            }
        }
        return cusID;
    }

    @Override
    public String getCustomerID() {
        return Utils.getAStringFormat(
                "Nhập mã khách hàng (KH-0000): ",
                "Sai định dạng! Mã khách hàng phải theo định dạng KH-xxxx.",
                "^KH-\\d{4}$"
        );
    }

    private static void printSeparator(char c, int... columnWidths) {
        StringBuilder separator = new StringBuilder();
        for (int width : columnWidths) {
            separator.append(c).append("-".repeat(width));
        }
        separator.append(c);
        System.out.println(separator);
    }

    private String center(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int leftPadding = (width - text.length()) / 2;
        int rightPadding = width - text.length() - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }

    public Customer updateCustomer() {
        Customer entities;
        String cusID;


        while (true) {
            cusID = getCustomerID();
            entities = customerService.findById(cusID);
            if (entities != null) {
                break;
            } else {
                System.out.println("This customer ID does not exist, please input a new ID.");
            }
        }

        boolean updating = true;
        while (updating) {
            Utils.getMessage("\n--- CHỌN TRƯỜNG CẦN CHỈNH SỬA ---");

            Field[] fields1 = entities.getClass().getSuperclass().getDeclaredFields();
            Field[] fields2 = entities.getClass().getDeclaredFields();
            int totalFields = fields1.length + fields2.length;

            for (int i = 0; i < fields1.length; i++) {
                System.out.println((i + 1) + ". " + fields1[i].getName());
            }
            for (int j = 0; j < fields2.length; j++) {
                System.out.println((j + 1 + fields1.length) + ". " + fields2[j].getName());
            }
            System.out.println("0. Thoát chỉnh sửa");

            int choice = Utils.inputInt("Get choice: ", 0, totalFields);

            switch (choice) {
                case 0:
                    System.out.println("Thoát chỉnh sửa.");
                    updating = false;
                    break;
                case 1:
                    entities.setName(Utils.getAString("Input new name of customer: ", "Invalid String"));
                    break;
                case 2:
                    entities.setDob(Utils.getALocalDate("Input new date of birth: ", "Invalid input"));
                    break;
                case 3:
                    entities.setSex(Utils.getAGender("What is your new gender (Nam | Nu | Khac): ", "Invalid input!"));
                    break;
                case 4:
                    entities.setCmnd(Utils.getAStringFormat("Input new ID Number: ", "ID card must have 9 or 12 digits", "^(\\d{9}|\\d{11})$"));
                    break;
                case 5:
                    entities.setPhoneNum(Utils.getAStringFormat("Input phone number: ", "Phone number must have 10 digits", "^0\\d{9}$"));
                    break;
                case 6:
                    entities.setEmail(Utils.getAStringFormat("Input email: ", "Invalid email!", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
                    break;
                case 7:
                    entities.setCustomerID(checkCusID());
                    break;
                case 8:
                    entities.setTypeCus(Validation.getValidCustomerType("Input Customer Type: ", "Error: Invalid input."));
                    break;
                case 9:
                    entities.setAddress(Utils.getAString("Input Address: ", "Invalid input"));
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }


            updating = Utils.checkInputYN("Do you want to continue updating this customer?");
        }

        return entities;
    }



}


