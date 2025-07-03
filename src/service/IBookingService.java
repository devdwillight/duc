package service;

import model.Booking;
import utils.CustomException;

public interface IBookingService extends Service<Booking> {
    @Override
    Booking findById(String id);

    @Override
    void add(Booking entity) throws CustomException;
}
