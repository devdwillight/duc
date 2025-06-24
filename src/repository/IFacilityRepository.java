package repository;

import model.Facility;

import java.util.ArrayList;
import java.util.Map;

public interface IFacilityRepository extends Repository<Facility, Map<Facility,Integer>> {

    @Override
    public void writeToFile(Map<Facility,Integer> entities);

    @Override
    public Map<Facility,Integer> readFromFile();

}
