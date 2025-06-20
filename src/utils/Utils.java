package utils;

import enums.Gender_options;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Utils {
    private static Scanner sc = new Scanner(System.in);

    public static void getMessage(String mess){
        System.out.println(mess);
    }

    public static String getAStringFormat(String inputMsg, String errorMsg, String regex){
        String n ;
        while (true){
            System.out.println(inputMsg);
            n = sc.nextLine().trim();
            if ( n.length() == 0 || n.isEmpty() || !n.matches(regex)){
                System.out.println(errorMsg);
            }else{
                return n;
            }
        }
    }

    public static boolean checkStringFormat(String stringCheck, String regex){
        if ( stringCheck !=null && !stringCheck.isEmpty() && stringCheck.matches(regex)){
            return true;
        }
        return false;
    }
    public static double inputDouble(String mess, double min, double max) {
        System.out.println(mess);
        while (true) {
            String input = sc.nextLine();
            try {
                double number = Double.parseDouble(input);
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
    public static int inputInt(String mess, int min, int max) {
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
    public static boolean checkInputYN(String mess) {
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
    public static String getAString( String mess, String errorMsg){
        String n ;
        while(true){
            System.out.println(mess);
            n = sc.nextLine().trim();
            if ( n.length() == 0 || n.isEmpty()){
                System.out.println(errorMsg);
            }else{
                return n;
            }
        }
    }
    public static LocalDate getALocalDate(String mess, String errorMsg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                System.out.print(mess);
                String input = sc.nextLine().trim();
                LocalDate date = LocalDate.parse(input, formatter);

                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Lỗi: Không được nhập ngày trong tương lai. Vui lòng thử lại.");
                    continue;
                }

                return date;

            } catch (DateTimeParseException e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static String getAGender (String mess, String errorMsg ){
        String n;
        while(true){
            System.out.println(mess);
            n = sc.nextLine().trim();
            if (Gender_options.isValid(n)){
                return n ;
            }else{
                System.out.println(errorMsg);
            }
        }

    }


}
