package repository;

import model.Booking;

import java.util.TreeSet;

public interface IBookingRepository extends Repository<Booking, TreeSet<Booking>> {
    @Override
    void writeToFile(TreeSet<Booking> enties);

    @Override
    TreeSet<Booking> readFromFile();
}
