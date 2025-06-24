package view;

import enums.*;
import utils.InputvalidationException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Validation {
    private static Scanner sc = new Scanner(System.in);
    private static final String EMPLOYEE_ID_REGEX = "^NV-\\d{4}$";
    private static final String NAME_REGEX = "^([A-Z][a-z]+)(\\s[A-Z][a-z]+)*$";
    public static final String CMND_REGEX = "^(\\d{9}|\\d{12})$";
    public static final String PHONE_NUM_REGEX = "^0\\d{9}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    // --- REGEX for Customer ---
    public static final String CUSTOMER_ID_REGEX = "^KH-\\d{4}$";
    public static final String CMND_CUSTOMER_REGEX = "^(\\d{9}|\\d{11})$";
    public static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public int inputInt(String mess, int min, int max) {
        System.out.println(mess);
        while (true) {
            String input = sc.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number < min || number > max) {
                    System.out.println("input between " + min + "and" + max + ":");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("input a number !");
            }
        }
    }

    public static String inputString(String mess, String regex) {
        System.out.println(mess);
        while (true) {
            String input = sc.nextLine();
            if (!input.matches(regex)) {
                System.out.println("Please input match regex(" + regex + ").");
                continue;
            }
            return input;
        }
    }



    public boolean checkInputYN(String mess) {
        System.out.println(mess);
        while (true) {
            String result = sc.nextLine();

            if (result.equalsIgnoreCase("Y")) {
                return true;
            }

            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }



    public static String validateEmployeeId(String input) throws InputvalidationException {
        if (!input.matches("^NV-\\d{4}$")) {
            throw new InputvalidationException("Invalid format!");
        }
        return input;
    }

    public static String validateName(String input) throws InputvalidationException {
        if (!input.matches("^([A-Z][a-z]+\\s?)+$")) {
            throw new InputvalidationException("Ten phai viet hoa chu dau");
        }
        return input;
    }

    public static LocalDate getValidDob(String prompt) {
        LocalDate dob;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(prompt + " (dd/MM/yyyy): ");
            String input = sc.nextLine();
            try {
                dob = LocalDate.parse(input, formatter);
                if (Period.between(dob, LocalDate.now()).getYears() >= 18) {
                    return dob;
                } else {
                    System.out.println("Lỗi: Nhân viên phải đủ 18 tuổi.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Định dạng ngày sinh không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
            }
        }
    }

    public static String validateIdCard(String input) throws InputvalidationException {
        if (!input.matches("^\\d{9}|\\d{12}$")) {
            throw new InputvalidationException("CMND phải có 9 hoặc 12 số.");
        }
        return input;
    }

    public static String validatePhone(String input) throws InputvalidationException {
        if (!input.matches("^0\\d{9}$")) {
            throw new InputvalidationException("SĐT phải bắt đầu bằng 0 và có 10 số.");
        }
        return input;
    }

    public static double validateSalary(String input) throws InputvalidationException {
        try {
            double salary = Double.parseDouble(input);
            if (salary <= 0) throw new InputvalidationException("Lương phải lớn hơn 0.");
            return salary;
        } catch (NumberFormatException e) {
            throw new InputvalidationException("Lương phải là số.");
        }
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static String getValidExperience(String prompt) {
        String input;
        while (true) {
            System.out.print(Arrays.stream(Experience_levels.values())
                    .map(Experience_levels::getDisplayName)
                    .collect(Collectors.joining(", ")));
            input = sc.nextLine();
            if (Experience_levels.isValid(input)) {
                return input;
            } else {
                System.out.println("Lỗi: Trình độ kinh nghiệm không hợp lệ. Vui lòng nhập một trong số: " + Arrays.stream(Experience_levels.values())
                        .map(Experience_levels::getDisplayName)
                        .collect(Collectors.joining(", ")));
            }
        }
    }

    /**
     * Kiểm tra và lấy vị trí công việc hợp lệ.
     */
    public static String getValidPosition(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt + " (" + String.join(", ", Arrays.stream(Valid_Positions.values())
                    .map(Valid_Positions::getDisplayName)
                    .collect(Collectors.joining(", "))) + "): ");
            input = sc.nextLine();
            if (Valid_Positions.isValid(input)) {
                return input;
            } else {
                System.out.println("Lỗi: Vị trí không hợp lệ. Vui lòng nhập một trong số: " + String.join(", ", Arrays.stream(Valid_Positions.values())
                        .map(Valid_Positions::getDisplayName)
                        .collect(Collectors.joining(", "))));
            }
        }
    }

    public static String getValidEmployeeId(String prompt) {
        return getValidString(prompt, EMPLOYEE_ID_REGEX, "Mã nhân viên phải đúng định dạng NV-YYYY (ví dụ: NV-0001).");
    }


    /**
     * Kiểm tra và lấy Mã khách hàng hợp lệ.
     */
    public static String getValidCustomerId(String prompt) {
        return getValidString(prompt, CUSTOMER_ID_REGEX, "Mã khách hàng phải đúng định dạng KH-YYYY (ví dụ: KH-0001).");
    }

    public static String getValidString(String prompt, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (Pattern.matches(regex, input)) {
                return input;
            } else {
                System.out.println("Lỗi: " + errorMessage);
            }
        }
    }

    public static double validateArea(String prompt) {
        double area;
        while (true) {
            System.out.println(prompt);
            area = sc.nextDouble();
            if (area > 0 && area <= 30) System.out.println("Diện tích phải lớn hơn 30m2.");
            else {
                return area;
            }
        }
    }

    public static String getValidCustomerCmnd(String prompt) {
        return getValidString(prompt, CMND_CUSTOMER_REGEX, "CMND khách hàng phải đủ 9 hoặc 11 số.");
    }

    public static LocalDate getValidCustomerDob(String prompt) {
        LocalDate dob;
        while (true) {
            System.out.print(prompt + " (dd/MM/yyyy): ");
            String input = sc.nextLine();
            try {
                dob = LocalDate.parse(input, DISPLAY_DATE_FORMATTER);
                if (Period.between(dob, LocalDate.now()).getYears() >= 20) {
                    return dob;
                } else {
                    System.out.println("Lỗi: Khách hàng phải đủ 20 tuổi.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Định dạng ngày sinh không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
            }
        }
    }

    public static String getValidCustomerType(String prompt, String error) {
        String input;
        while (true) {
            System.out.print(prompt + " (" + String.join(", ", Arrays.stream(Customer_types.values())
                    .map(Customer_types::getDisplayName)
                    .collect(Collectors.joining(", "))) + "): ");
            input = sc.nextLine();

            if (Customer_types.isValid(input)) {
                return input;
            } else {
                System.out.println(error + String.join(", ", Arrays.stream(Customer_types.values())
                        .map(Customer_types::getDisplayName)
                        .collect(Collectors.joining(", "))));
            }
        }
    }

    // Getter cho DateTimeFormatter (để sử dụng trong Repository)
    public static DateTimeFormatter getFileDateFormatter() {
        return FILE_DATE_FORMATTER;
    }

    public static DateTimeFormatter getDisplayDateFormatter() {
        return DISPLAY_DATE_FORMATTER;
    }

    public static String getValidSex(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt + " (" + String.join(", ", Arrays.stream(Gender_options.values())
                    .map(Gender_options::getDisplayName)
                    .collect(Collectors.joining(", "))) + "): ");
            input = sc.nextLine();
            if (Gender_options.isValid(input)) {
                return input;
            } else {
                System.out.println("Lỗi: Giới tính không hợp lệ. Vui lòng nhập một trong số: " + String.join(", ", Arrays.stream(Gender_options.values())
                        .map(Gender_options::getDisplayName)
                        .collect(Collectors.joining(", "))));
            }
        }
    }

    public static String getValidServiceType(String prompt, String error) {
        String input;
        while (true) {
            System.out.print(prompt + " (" + String.join(", ", Arrays.stream(Type_Service.values())
                    .map(Type_Service::getDisplayName)
                    .collect(Collectors.joining(", "))) + "): ");
            input = sc.nextLine();

            if (Type_Service.isValid(input)) {
                return input;
            } else {
                System.out.println(error + String.join(", ", Arrays.stream(Type_Service.values())
                        .map(Type_Service::getDisplayName)
                        .collect(Collectors.joining(", "))));
            }
        }
    }
    public static String getValidCRentType(String prompt, String error) {
        String input;
        while (true) {
            System.out.print(prompt + " (" + String.join(", ",
                    Arrays.stream(Rent_Type.values())
                            .map(Rent_Type::getDisplayName)
                            .collect(Collectors.toList())) + "): ");
            input = sc.nextLine();

            if (Rent_Type.isValid(input)) {
                return input;
            } else {
                System.out.println(error + String.join(", ",
                        Arrays.stream(Rent_Type.values())
                                .map(Rent_Type::getDisplayName)
                                .collect(Collectors.toList())));
            }
        }
    }

}


