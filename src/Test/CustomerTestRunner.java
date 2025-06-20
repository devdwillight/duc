package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import service.CustomerService;
public class CustomerTestRunner {

    public static void main(String[] args) {
        String filePath = "src/data/customerTestCase.txt"; // hoặc đường dẫn đầy đủ

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 để không bỏ qua trường rỗng

                String tcId = parts[0];
                String description = parts[1];
                int id = Integer.parseInt(parts[2]);
                String name = parts[3];
                String birthday = parts[4];
                String gender = parts[5];
                String phone = parts[6];
                String email = parts[7];
                String address = parts[8];
                String type = parts[9];
                String expected = parts[10];

                String actual = CustomerService.createCustomer(id, name, birthday, gender, phone, email, address, type);

                System.out.printf("%s - %s\n", tcId, description);
                System.out.printf("Expected: %s | Actual: %s => %s\n\n", expected, actual, expected.equals(actual) ? "PASSED" : "FAILED");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
