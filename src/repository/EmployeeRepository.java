package repository;

import model.Employee;
import utils.CustomException;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {
    private static final String FILE_PATH = "/data/employee.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng ngày tháng trong fil

    public EmployeeRepository() {
        // Đảm bảo file tồn tại khi khởi tạo repository
        File file = new File( path+ FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Lỗi khi tạo file employee.csv: " + e.getMessage());
            }
        }
    }

    @Override
    public void writeToFile(ArrayList<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + FILE_PATH))) {
            for (Employee employee : employees) {
                bw.write(String.join(",",
                        employee.getEmployeeID(),
                        employee.getName(),
                        employee.getDob().format(DATE_FORMATTER),
                        employee.getSex(),
                        employee.getCmnd(),
                        employee.getPhoneNum(),
                        employee.getEmail(),
                        employee.getExperience(),
                        employee.getPosition(),
                        String.valueOf(employee.getSalary())
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file employee.txt: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Employee> readFromFile() {
        ArrayList<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader( path + FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống
                String[] data = line.split(",");
                if (data.length == 10) { // Đảm bảo đủ số lượng cột
                    try {
                        String employeeID = data[0];
                        String name = data[1];
                        LocalDate dob = LocalDate.parse(data[2], DATE_FORMATTER);
                        String sex = data[3];
                        String cmnd = data[4];
                        String phoneNum = data[5];
                        String email = data[6];
                        String experience = data[7];
                        String position = data[8];
                        double salary = Double.parseDouble(data[9]);

                        employees.add(new Employee(employeeID, name, dob, sex, cmnd, phoneNum, email, experience, position, salary));
                    } catch (DateTimeException | NumberFormatException e) {
                        System.err.println("Lỗi định dạng dữ liệu trong file: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Lỗi định dạng dòng trong file (số cột không đúng): " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file employee.csv: " + e.getMessage());
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        ArrayList<Employee> employees = readFromFile();
        employees.add(employee);
        writeToFile(employees);
    }


    public void updateEmployee(Employee updatedEmployee) throws CustomException {
        ArrayList<Employee> employees = readFromFile();
        boolean found = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(updatedEmployee.getEmployeeID())) {
                employees.set(i, updatedEmployee);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new CustomException("Không tìm thấy nhân viên với ID: " + updatedEmployee.getEmployeeID());
        }
       writeToFile(employees);
    }
}
