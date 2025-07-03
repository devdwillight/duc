package repository;

import com.sun.source.doctree.SeeTree;
import model.Booking;

import java.util.Set;
import java.util.TreeSet;

public interface IPromotionRepository {

   Set<Booking>getBookingsByYear(String year);
   Set<Booking>getBookingsInCurrentMonth();

}
