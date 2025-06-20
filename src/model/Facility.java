package model;

public class Facility {
    protected String facilityID ;
    protected String nameService ;
    protected double userArea ;
    protected double cost ;
    protected int maxPerson ;
    protected double type ;

    public Facility(String facilityID, String nameService, double userArea, double cost, int maxPerson, double type) {
        this.facilityID = facilityID;
        this.nameService = nameService;
        this.userArea = userArea;
        this.cost = cost;
        this.maxPerson = maxPerson;
        this.type = type;
    }

    public Facility() {
    }
}
