package service;

import model.Booking;
import repository.PromotionRepository;
import utils.BookingComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class PromotionService implements IPromotionService {
    private final PromotionRepository promotionRepository = new PromotionRepository();

    @Override
    public Set<Booking> displayCustomersUseServiceByYear(String year) {
        return promotionRepository.getBookingsByYear(year);
    }

    @Override
    public Stack<Booking> displayCustomersGetVoucher(int v10, int v20, int v50) {
        Set<Booking> currentMonthBookings = promotionRepository.getBookingsInCurrentMonth();
        int totalVoucher = v10 + v20 + v50;

        List<Booking> sortedBookings = new ArrayList<>(currentMonthBookings)
                .stream()
                .sorted(new BookingComparator())
                .collect(Collectors.toList());

        Stack<Booking> resultStack = new Stack<>();


        int startIndex = Math.max(0, sortedBookings.size() - totalVoucher);
        for (int i = startIndex; i < sortedBookings.size(); i++) {
            resultStack.push(sortedBookings.get(i));
        }

        return resultStack;
    }
}
