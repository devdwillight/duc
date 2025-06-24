package model;

public class Villa extends Facility {
    private String standard;
    private double poolArea ;
    private int floor;

    public Villa(String facilityID, String nameService, double userArea, double cost, int maxPerson, String type, String standard, double poolArea, int floor) {
        super(facilityID, nameService, userArea, cost, maxPerson, type);
        this.standard = standard;
        this.poolArea = poolArea;
        this.floor = floor;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    @Override
    public String toString() {
        return getFacilityID() + "," + getNameService() + "," + getUserArea() + "," +
                getCost() + "," + getMaxPerson() + "," + getType() + "," +
                standard + "," + poolArea + "," + floor;
    }


}
