package model;

public abstract class Facility {
    protected String facilityID ;
    protected String nameService ;
    protected double userArea ;
    protected double cost ;
    protected int maxPerson ;
    protected String type ;

    public Facility(String facilityID, String nameService, double userArea, double cost, int maxPerson, String type) {
        this.facilityID = facilityID;
        this.nameService = nameService;
        this.userArea = userArea;
        this.cost = cost;
        this.maxPerson = maxPerson;
        this.type = type;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public double getUserArea() {
        return userArea;
    }

    public void setUserArea(double userArea) {
        this.userArea = userArea;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Facility() {
    }



}
