package repository;

import model.Booking;
import model.Customer;
import utils.BookingComparator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

public class BookingRepository implements IBookingRepository {
    private static final String FILE_PATH = "/data/booking.txt";
    private static TreeSet<Booking> bookings = new TreeSet<>(new BookingComparator());
    static {
        File file = new File(path+FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Không thể tạo file booking.csv");
            }
        }
    }

    @Override
    public void writeToFile(TreeSet<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path+FILE_PATH))) {
            for (Booking b : bookings) {
                writer.write(b.getBookingID() + "," +
                        b.getBookingDay() + "," +
                        b.getStartDay() + "," +
                        b.getEndDay() + "," +
                        b.getCusID() + "," +
                        b.getServiceID());
                writer.newLine();

            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file Booking: " + e.getMessage());
        }
    }

    @Override
    public TreeSet<Booking> readFromFile() {
        TreeSet<Booking> bookings = new TreeSet<>(new BookingComparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(path+FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id = parts[0].trim();
                    LocalDate bookingDay = LocalDate.parse(parts[1].trim());
                    LocalDate start = LocalDate.parse(parts[2].trim());
                    LocalDate end = LocalDate.parse(parts[3].trim());
                    String cusID = parts[4].trim();
                    String serviceID = parts[5].trim();

                    bookings.add(new Booking(id, bookingDay, start, end, cusID, serviceID));
                }
            }
            System.out.println(">>> doc file bookings thành công."+ (path + FILE_PATH));
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file Booking: " + e.getMessage());
        }
        return bookings;
    }
    public void addBooking(Booking booking) {
        TreeSet<Booking> bookings= readFromFile();
        bookings.add(booking);
        writeToFile(bookings);
    }
}
