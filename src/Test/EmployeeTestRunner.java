package Test;

import service.EmployeeService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeTestRunner {
    public static void main(String[] args) {
        // Đường dẫn đến file test case
        String filePath = "src/data/EmployeeTestCase.txt"; // Hoặc "src/data/employeeTestCase.txt" tùy môi trường chạy

        // Tạo một đối tượng EmployeeService để gọi phương thức createEmployee
        EmployeeService employeeService = new EmployeeService();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Đọc từng dòng trong file test case
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 để không bỏ qua trường rỗng

                // Phân tích các phần của dòng thành dữ liệu test
                String tcId = parts[0];
                String description = parts[1];
                String employeeID = parts[2];
                String name = parts[3];
                String birthday = parts[4];
                String gender = parts[5];
                String cmnd = parts[6];
                String phoneNum = parts[7];
                String email = parts[8];
                String experience = parts[9];
                String position = parts[10];
                String salary = parts[11];
                String expected = parts[12]; // Kết quả mong đợi từ test case

                // Gọi phương thức createEmployee từ EmployeeService và lấy kết quả thực tế
                String actual = employeeService.createEmployee(employeeID, name, birthday, gender,
                        cmnd, phoneNum, email,
                        experience, position, salary);

                // In kết quả test case
                System.out.printf("TC ID: %s | %s\n", tcId, description);
                System.out.printf("    Expected: \"%s\"\n", expected);
                System.out.printf("    Actual:   \"%s\"\n", actual);
                System.out.printf("    Status:   %s\n\n", expected.equals(actual) ? "PASSED" : "FAILED");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file test case: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi không mong muốn trong Test Runner: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
