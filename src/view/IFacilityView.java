package view;

import model.Facility;

import java.util.ArrayList;
import java.util.Map;

public interface IFacilityView extends View<Facility> {
    @Override
    public void display(ArrayList<Facility>entities);

    @Override
    public Facility getDetail();

    void display(Map<Facility, Integer>map);
}
