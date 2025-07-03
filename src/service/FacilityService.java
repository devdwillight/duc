package service;

import model.Booking;
import model.Customer;
import model.Employee;
import model.Facility;
import repository.FacilityRepository;
import utils.CustomException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class FacilityService implements IFacilityService{
    FacilityRepository facilityRepository = new FacilityRepository();


    @Override
    public Facility findById(String id) {
        Map<Facility, Integer> map = facilityRepository.readFromFile();
        for (Facility facility : map.keySet()) {
            if (facility.getFacilityID() .equals(id)) {
                return facility;
            }
        }
        return null;
    }

    @Override
    public void add(Facility entity) throws CustomException {
    facilityRepository.addFacility(entity);
    }

    @Override
    public void save() {

    }

    @Override
    public void useFacility(Facility facility) {
        Map<Facility, Integer> map = facilityRepository.readFromFile();
        for (Facility key : map.keySet()) {
            if (key.getFacilityID().equals(facility.getFacilityID())) {
                int currentUsage = map.get(key);
                map.put(key, currentUsage + 1);
                break;
            }
        }
        System.out.println(map.get(facility));
        facilityRepository.writeToFile(map); // ⚠️ Ghi lại vào file
    }



    @Override
    public void displayMaintenance() {
        Map<Facility, Integer> map = facilityRepository.readFromFile();
        boolean hasMaintenance = false;

        for (Map.Entry<Facility, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 5) {
                System.out.println("⚠ CẦN BẢO TRÌ: " + entry.getKey().getNameService()
                        + " | Mã: " + entry.getKey().getFacilityID()
                        + " | Số lần sử dụng: " + entry.getValue());
                hasMaintenance = true;
            }
        }

        if (!hasMaintenance) {
            System.out.println("✅ Hiện tại không có dịch vụ nào cần bảo trì.");
        }
    }

    @Override
    public Map<Facility, Integer> updateUsageFromBookingsThisMonth() {
        BookingService bookingService = new BookingService();
        TreeSet<Booking> allBookings = bookingService.findAll();
        Map<Facility, Integer> facilityUsage = new LinkedHashMap<>();

        YearMonth currentMonth = YearMonth.now();

        for (Booking booking : allBookings) {
            LocalDate startDay = booking.getStartDay();
            if (YearMonth.from(startDay).equals(currentMonth)) {
                Facility facility = findById(booking.getServiceID());
                if (facility != null) {
                    facilityUsage.put(facility, facilityUsage.getOrDefault(facility, 0) + 1);
                }
            }
        }

        return facilityUsage;
    }


    public Map<Facility, Integer> findAll() {
        return facilityRepository.readFromFile();
    }

    public void displayMaintenanceMonth() {
        Map<Facility, Integer> usageMap = updateUsageFromBookingsThisMonth();
        boolean hasMaintenance = false;

        for (Map.Entry<Facility, Integer> entry : usageMap.entrySet()) {
            if (entry.getValue() >= 5) {
                System.out.println("CẦN BẢO TRÌ: " + entry.getKey() +
                        " - Số lần sử dụng: " + entry.getValue());
                hasMaintenance = true;
            }
        }

        if (!hasMaintenance) {
            System.out.println("KHÔNG CÓ CƠ SỞ NÀO CẦN BẢO TRÌ.");
        }
    }





}
