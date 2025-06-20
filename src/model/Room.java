package model;

public class Room extends Facility {
    private String freeService ;

    public Room(String facilityID, String nameService, double userArea, double cost, int maxPerson, double type, String freeService) {
        super(facilityID, nameService, userArea, cost, maxPerson, type);
        this.freeService = freeService;
    }
}
