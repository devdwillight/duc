package view;

import model.Booking;
import model.Contract;
import service.BookingService;
import service.ContractService;
import utils.Utils;

import java.util.ArrayList;
import java.util.Queue;

public class ContractView implements IContractView{
    BookingService bookingService = new BookingService();
    ContractService contractService = new ContractService();
    @Override
    public void display(ArrayList<Contract> entities) {

    }
    @Override
    public Contract getDetail() {
        return null;
    }
    @Override
    public Contract getDetail(Booking booking) {
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



    @Override
    public void display(Queue<Contract> contracts) {
        if (contracts.isEmpty()) {
            System.out.println("Không có hợp đồng nào.");
            return;
        }

        for (Contract contract : contracts) {
            System.out.println(contract);
        }
    }
    public void editContract() {
        Queue<Contract> contractList = contractService.findAll();

        if (contractList.isEmpty()) {
            System.out.println("Danh sách hợp đồng trống.");
            return;
        }

        System.out.println("Danh sách hợp đồng:");
        for (Contract contract : contractList) {
            System.out.println(contract);
        }

        String id = Utils.getAStringFormat("Nhập mã hợp đồng muốn sửa: ", "Contract ID must be in the correct form KH-0000", "^HD-\\d{4}$");
        Contract contract = contractService.findById(id);

        if (contract == null) {
            System.out.println("Không tìm thấy hợp đồng với mã: " + id);
            return;
        }

        try {

            double deposit = Utils.inputDouble("Nhập tiền đặt cọc mới: ","Invalid input!");

            double total = Utils.inputDouble("Nhập tổng tiền thanh toán mới: ","Invalid input!");

            contract.setDepositAmount(deposit);
            contract.setTotalPayment(total);

            contractService.updateContract(contract);
            System.out.println("Cập nhật hợp đồng thành công.");
        } catch (NumberFormatException e) {
            System.out.println("Giá trị nhập không hợp lệ. Vui lòng nhập số.");
        }
    }





}
