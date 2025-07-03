package model;

import java.time.LocalDate;
import java.util.Objects;

public class Booking implements Comparable<Booking> {
    private String bookingID;
    private LocalDate bookingDay;
    private LocalDate startDay;
    private LocalDate endDay;
    private String cusID;
    private String serviceID;

    public Booking(String bookingID, LocalDate bookingDay, LocalDate startDay,
                   LocalDate endDay, String cusID, String serviceID) {
        this.bookingID = bookingID;
        this.bookingDay = bookingDay;
        this.startDay = startDay;
        this.endDay = endDay;
        this.cusID = cusID;
        this.serviceID = serviceID;
    }

    // Getter/Setter
    public String getBookingID() {
        return bookingID;
    }

    public LocalDate getBookingDay() {
        return bookingDay;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public String getCusID() {
        return cusID;
    }

    public String getServiceID() {
        return serviceID;
    }

    // Override equals() và hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return bookingID.equals(booking.bookingID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID);
    }

    // Optional: so sánh theo ngày nếu dùng TreeSet mà không cần Comparator
    @Override
    public int compareTo(Booking o) {
        int cmp = this.startDay.compareTo(o.startDay);
        if (cmp == 0) {
            return this.endDay.compareTo(o.endDay);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return bookingID + "," + bookingDay + "," + startDay + "," + endDay + "," + cusID + "," + serviceID;
    }
}
