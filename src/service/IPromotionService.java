package service;

import model.Booking;

import java.util.Set;
import java.util.Stack;

public interface IPromotionService {
    public Set<Booking> displayCustomersUseServiceByYear(String year);
    public Stack<Booking>displayCustomersGetVoucher(int v10, int v20, int v50);
}
