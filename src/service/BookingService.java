package service;

import model.Booking;
import model.Contract;
import model.Facility;
import repository.BookingRepository;
import repository.ContrastRepository;
import utils.CustomException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class BookingService implements IBookingService {
    BookingRepository bookingRepository = new BookingRepository();
    ContrastRepository contrastRepository = new ContrastRepository();
    FacilityService facilityService = new FacilityService();

    @Override
    public Booking findById(String id) {
        TreeSet<Booking> bookings = bookingRepository.readFromFile();
        for (Booking booking : bookings) {
            if (booking.getBookingID().equals(id)) {
                return booking;
            }
        }
        return null; // Không tìm thấy
    }


    @Override
    public void add(Booking entity) throws CustomException {
    bookingRepository.addBooking(entity);
        Facility facility = facilityService.findById(entity.getServiceID());
        if (facility != null) {
            facilityService.useFacility(facility);
        } else {
            System.out.println("Không tìm thấy dịch vụ với ID: " + entity.getServiceID());
        }
    }

    @Override
    public void save() {

    }
    public TreeSet<Booking> findAll() {
        return bookingRepository.readFromFile();
    }

    public Booking getNextBooking() {
        Queue<Booking> queue = new LinkedList<>(bookingRepository.readFromFile());
        return queue.poll();
    }
    public void saveContract(Contract contract) {
       contrastRepository.addContract(contract);
    }



}
