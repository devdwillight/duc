package view;

import model.Customer;
import model.Employee;
import service.EmployeeService;

import java.util.ArrayList;


public class EmployeeView implements IEmployeeView {
    EmployeeService employeeService = new EmployeeService();

    @Override
    public void display(ArrayList<Employee> entities) {
        if (entities == null || entities.isEmpty()) {
            System.out.println("Không có Nhan vien nào để hiển thị.");
            return;
        }
            printSeparator('+', 12, 20, 12, 10, 15, 25, 10, 15, 25,25,20,15);
            System.out.printf("| %-10s | %-18s | %-10s | %-8s | %-13s | %-23s | %-8s | %-13s | %-23s |  %-23s |  %-23s |  %-10f\n",
                    "Mã KH", "Tên KH", "Ngày sinh", "Giới tính", "CMND", "Email", "SĐT", "Loại", "Địa chỉ");
            printSeparator('+', 12, 20, 12, 10, 15, 25, 10, 15, 25);

            for (Employee c : entities) {
                System.out.printf("| %-10s | %-18s | %-10s | %-8s | %-13s | %-23s | %-8s | %-13s | %-23s |\n",
                        c.getEmployeeID(), c.getName(), c.getDob().format(Validation.DISPLAY_DATE_FORMATTER), c.getSex(),
                        c.getCmnd(), c.getEmail(), c.getPhoneNum(),c.getExperience(),c.getPosition(),c.getSalary() );
            }

            printSeparator('+', 12, 20, 12, 10, 15, 25, 10, 15, 25);
        }
/// /hellloooo

    @Override
    public Employee getDetail() {
        return null;
    }

    @Override
    public String getEmployeeID() {
        return "";
    }
    private static void printSeparator(char c, int... columnWidths) {
        StringBuilder separator = new StringBuilder();
        for (int width : columnWidths) {
            separator.append(c).append("-".repeat(width));
        }
        separator.append(c);
        System.out.println(separator);
    }
}
