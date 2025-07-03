package utils;

import model.Booking;

import java.util.Comparator;

public class BookingComparator implements Comparator<Booking> {
    @Override
    public int compare(Booking b1, Booking b2) {
        int compareBookingDay = b2.getBookingDay().compareTo(b1.getBookingDay());
        if (compareBookingDay != 0) {
            return compareBookingDay;
        }
        // Nếu booking day trùng nhau, so sánh giảm dần endDate
        return b2.getEndDay().compareTo(b1.getEndDay());
    }
}
