package service;

import model.Customer;
import model.Employee;
import model.Facility;
import repository.FacilityRepository;
import utils.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if (map.containsKey(facility)) {
            int currentUsage = map.get(facility);
            map.put(facility, currentUsage + 1);
        }
        facilityRepository.writeToFile(map);
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
    public Map<Facility, Integer> findAll() {
        return facilityRepository.readFromFile();
    }



}
