package model;

public class Villa extends Facility {
    private String standard;
    private double poolArea ;
    private int floor;

    public Villa(String facilityID, String nameService, double userArea, double cost, int maxPerson, double type, String standard, double poolArea, int floor) {
        super(facilityID, nameService, userArea, cost, maxPerson, type);
        this.standard = standard;
        this.poolArea = poolArea;
        this.floor = floor;
    }

}
