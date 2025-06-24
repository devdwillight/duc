package model;

public class Room extends Facility {
    private String freeService ;

    public Room(String facilityID, String nameService, double userArea, double cost, int maxPerson, String type, String freeService) {
        super(facilityID, nameService, userArea, cost, maxPerson, type);
        this.freeService = freeService;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }
    @Override
    public String toString() {
        return getFacilityID() + "," + getNameService() + "," + getUserArea() + "," +
                getCost() + "," + getMaxPerson() + "," + getType() + "," +
                freeService;
    }


}
