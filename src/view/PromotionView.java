package view;

import model.Booking;
import service.PromotionService;
import utils.Utils;

import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class PromotionView implements IPromotionView {
    private final Scanner scanner = new Scanner(System.in);
    private final PromotionService promotionService = new PromotionService();

    public String inputYear() {
        return Utils.getAString("Nhập năm cần xem khách hàng đã sử dụng dịch vụ: ","Invalid!");
    }

    @Override
    public void disPlayCustomersUseService(Set<Booking> bookingSet) {
        if (bookingSet.isEmpty()) {
            System.out.println(" Không có khách hàng nào sử dụng dịch vụ trong năm này.");
            return;
        }
        System.out.println("=== Danh sách khách hàng đã sử dụng dịch vụ ===");
        int count = 1;
        for (Booking booking : bookingSet) {
            String cusID = booking.getCusID();
            String serviceID = booking.getServiceID();
            String date = booking.getStartDay().toString();
            System.out.printf("%d. %s - Dịch vụ: %s - Ngày: %s\n", count++, cusID, serviceID, date);
        }
    }

    public int inputVoucher(String percent) {
        return Integer.parseInt(Utils.getAString("Nhập số lượng voucher " + percent + "%: ","Invalid!"));
    }

    @Override
    public void disPlayCustomersGetVoucher(Stack<Booking> bookingStack, int v10, int v20, int v50) {
        System.out.println("=== Danh sách khách hàng nhận voucher ===");
        int count = 0;
        int total = v10 + v20 + v50;
        while (!bookingStack.isEmpty() && count < total) {
            Booking booking = bookingStack.pop();
            String customerID = booking.getCusID();
            String voucher;

            if (count < v10) voucher = "50%";
            else if (count < v10 + v20) voucher = "20%";
            else voucher = "10%";

            System.out.printf("%d. %s - nhận voucher %s\n", count + 1, customerID, voucher);
            count++;
        }
    }

    public void notifyVoucherExceedsCustomer() {
        System.out.println(" Số lượng voucher vượt quá số lượng khách hàng sử dụng dịch vụ trong tháng!");
    }

    public void displayCustomersGetVoucher() {
        int v10 = inputVoucher("10");
        int v20 = inputVoucher("20");
        int v50 = inputVoucher("50");

        Stack<Booking> result = promotionService.displayCustomersGetVoucher(v10, v20, v50);
        if (result.size() < (v10 + v20 + v50)) {
            notifyVoucherExceedsCustomer();
        } else {
            disPlayCustomersGetVoucher(result, v10, v20, v50);
        }
    }
    public void displayCustomersUsedServiceByYear() {
        String year = inputYear();
        Set<Booking> bookings = promotionService.displayCustomersUseServiceByYear(year);
        disPlayCustomersUseService(bookings);
    }

}
