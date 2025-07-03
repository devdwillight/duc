package view;

import model.Booking;
import model.Contract;
import service.ContractService;
import service.CustomerService;
import service.FacilityService;
import utils.Utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;

public class BookingView implements IBookingView {
    CustomerService customerService = new CustomerService();
    CustomerView customerView = new CustomerView();
    FacilityService facilityService = new FacilityService();
    FacilityView facilityView = new FacilityView();
    ContractService contractService = new ContractService();


    @Override
    public void display(ArrayList<Booking> entities) {
    }

    @Override
    public void display(TreeSet<Booking> entities) {
        if (entities == null || entities.isEmpty()) {
            System.out.println("Không có dữ liệu booking để hiển thị.");
            return;
        }

        System.out.printf("%-12s %-15s %-15s %-15s %-10s %-15s\n",
                "Booking ID", "Booking Day", "Start Day", "End Day", "Cus ID", "Service ID");
        System.out.println("----------------------------------------------------------------------------------");

        for (Booking b : entities) {
            System.out.printf("%-12s %-15s %-15s %-15s %-10s %-15s\n",
                    b.getBookingID(),
                    b.getBookingDay(),
                    b.getStartDay(),
                    b.getEndDay(),
                    b.getCusID(),
                    b.getServiceID());
        }
    }



    @Override
    public Booking getDetail() {
        System.out.println("=== Add New Booking ===");
        System.out.println("Choice Customer:");
        customerView.display(customerService.findAll());
        String cusId = Utils.getAStringFormat("Input Customer ID : ", "Mã khách hàng phải đúng định dạng KH-YYYY (ví dụ: KH-0001).","^KH-\\d{4}$");
        System.out.println("Choice Facility :");
        facilityView.display(facilityService.findAll());
        String facilityID = Utils.getAStringFormat("Input Facility_ID : ", "Facility ID must be in the correct form SVXX-0000", "^SV(VL|HO|RO)-\\d{4}$");
        LocalDate bookingDate;
        LocalDate bookingDate_Start;
        LocalDate bookingDate_End;
        while (true) {
            bookingDate = Utils.getALocalDate("Input Day booking (dd/MM/yyyy)", "Invalid Day");
            bookingDate_Start = Utils.getALocalDate("Input Day_Start (dd/MM/yyyy)", "Invalid Day");
            bookingDate_End = Utils.getALocalDate("Input Day_End (dd/MM/yyyy)", "Invalid Day");

            LocalDate now = LocalDate.now();
            YearMonth currentYearMonth = YearMonth.from(now);


            if (YearMonth.from(bookingDate).isBefore(currentYearMonth) ||
                    YearMonth.from(bookingDate_Start).isBefore(currentYearMonth) ||
                    YearMonth.from(bookingDate_End).isBefore(currentYearMonth)) {
                System.out.println(" Ngày không được nằm trong tháng hoặc năm quá khứ!");
            } else if (bookingDate.isAfter(bookingDate_Start)) {
                System.out.println(" Ngày booking phải trước hoặc bằng ngày bắt đầu!");
            } else if (bookingDate_Start.isAfter(bookingDate_End)) {
                System.out.println(" Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
            } else {
                break; // Hợp lệ
            }

            System.out.println(" Vui lòng nhập lại các ngày hợp lệ.");
        }

        String bookingId = "BK" + UUID.randomUUID().toString().substring(0, 5);
        Booking booking = new Booking(bookingId, bookingDate, bookingDate_Start, bookingDate_End, cusId, facilityID);
        return booking;
    }

    public Contract inputContract(Booking booking) {
        System.out.println("Tạo hợp đồng cho booking: " + booking.getBookingID());

        String id = checkID();

        double deposit = Utils.inputDouble("So tien dat coc","Invalid input!");

        double total = Utils.inputDouble("Tong tien thanh toan","Invalid input!");
        return new Contract(id, booking.getBookingID(), deposit, total);
    }
    public String checkID(){
        String ConID;
        while (true) {
            ConID = Utils.getAStringFormat("Input Contract ID: ", "Contract ID must be in the correct form HD-0000", "^HD-\\d{4}$");
            if (contractService.findById(ConID) == null) {
                break;
            } else {
                System.out.println("This Contract ID  already exits, please input a new ID");
            }
        }
        return ConID;
    }
}
