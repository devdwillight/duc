package repository;

import model.Booking;
import utils.BookingComparator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PromotionRepository implements IPromotionRepository {
    private static final TreeSet<Booking> customerUsedServiceSet = new TreeSet<>(new BookingComparator());

    static {
        BookingRepository bookingRepository = new BookingRepository();
        Set<Booking> bookings = bookingRepository.readFromFile();
        customerUsedServiceSet.addAll(bookings);
    }

    @Override
    public Set<Booking> getBookingsByYear(String year) {
        int yearInt = Integer.parseInt(year);
        return customerUsedServiceSet.stream()
                .filter(b -> b.getStartDay().getYear() == yearInt)
                .collect(Collectors.toCollection(() -> new TreeSet<>(new BookingComparator())));
    }

    @Override
    public Set<Booking> getBookingsInCurrentMonth() {
        YearMonth current = YearMonth.now();
        return customerUsedServiceSet.stream()
                .filter(b ->YearMonth.from(b.getStartDay()).equals(current))
                .collect(Collectors.toCollection(()-> new TreeSet<>(new BookingComparator())));
    }

}
