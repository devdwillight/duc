package model;

public class House extends Facility {
    private String standard;
    private int floor;

    public House(String facilityID, String nameService, double userArea, double cost, int maxPerson, double type, String standard, int floor) {
        super(facilityID, nameService, userArea, cost, maxPerson, type);
        this.standard = standard;
        this.floor = floor;
    }
}
