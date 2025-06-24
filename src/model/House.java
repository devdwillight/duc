package model;

public class House extends Facility {
    private String standard;
    private int floor;

    public House(String facilityID, String nameService, double userArea, double cost, int maxPerson, String type, String standard, int floor) {
        super(facilityID, nameService, userArea, cost, maxPerson, type);
        this.standard = standard;
        this.floor = floor;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
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
                standard + "," + floor;
    }


}
