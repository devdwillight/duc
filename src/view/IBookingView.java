package view;

import model.Booking;

import java.util.ArrayList;
import java.util.TreeSet;

public interface IBookingView extends View<Booking> {

    void display(TreeSet<Booking> entities);

    Booking getDetail();
}
