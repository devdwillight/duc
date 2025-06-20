package model;

import java.time.LocalDate;

public class Booking {
    private String bookingID;
    private LocalDate bookingDay;
    private LocalDate startDay;
    private LocalDate endDay ;
    private String cusID ;
    private String madichvu ;
    Facility facility = new Facility() ;

    public Booking(String bookingID, LocalDate bookingDay, LocalDate startDay, LocalDate endDay, String cusID, String madichvu) {
        this.bookingID = bookingID;
        this.bookingDay = bookingDay;
        this.startDay = startDay;
        this.endDay = endDay;
        this.cusID = cusID;
        this.madichvu = madichvu;
    }

}
