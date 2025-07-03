package service;

import model.Facility;
import utils.CustomException;

import java.util.Map;

public interface IFacilityService extends Service<Facility> {
    @Override
    Facility findById(String id);

    @Override
    void add(Facility entity) throws CustomException;

    @Override
    void save();

    void useFacility(Facility facility);

    void displayMaintenance();

    Map<Facility, Integer> updateUsageFromBookingsThisMonth();
}
