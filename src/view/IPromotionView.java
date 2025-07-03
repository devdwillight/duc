package view;

import model.Booking;

import java.util.Set;
import java.util.Stack;

public interface IPromotionView {
    public void disPlayCustomersUseService(Set<Booking>bookingSet);

    public void disPlayCustomersGetVoucher(Stack<Booking> bookingStack, int v10, int v20, int v50);
}
