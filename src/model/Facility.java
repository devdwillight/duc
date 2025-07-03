package model;

public abstract class Facility {
    protected String facilityID ;
    protected String nameService ;
    protected double userArea ;
    protected double cost ;
    protected int maxPerson ;
    protected String type ;
    private int timesUsed;

    public Facility(String facilityID, String nameService, double userArea, double cost, int maxPerson, String type, int timesUsed) {
        this.facilityID = facilityID;
        this.nameService = nameService;
        this.userArea = userArea;
        this.cost = cost;
        this.maxPerson = maxPerson;
        this.type = type;
        this.timesUsed= timesUsed;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Facility facility = (Facility) o;

        return facilityID!= null ? facilityID.equals(facility.facilityID) : facility.facilityID == null;
    }

    @Override
    public int hashCode() {
        return facilityID != null ? facilityID.hashCode() : 0;
    }



}
