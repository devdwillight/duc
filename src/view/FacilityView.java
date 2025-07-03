package view;

import model.Facility;
import model.House;
import model.Room;
import model.Villa;
import service.FacilityService;
import utils.Utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class FacilityView implements IFacilityView {

    FacilityService facilityService = new FacilityService();


    @Override
    public void display(Map<Facility, Integer> map) {
        if (map == null || map.isEmpty()) {
            System.out.println("Không có dịch vụ nào để hiển thị.");
            return;
        }

        System.out.printf("| %-8s | %-20s | %-8s | %-10s | %-10s | %-10s | %-10s | %-25s | %-10s |\n",
                "Mã DV", "Tên DV", "Diện tích", "Giá thuê", "Số người", "Loại thuê", "Số tầng", "Khác", "Đã dùng");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<Facility, Integer> entry : map.entrySet()) {
            Facility f = entry.getKey();


            String extra1 = "";
            String extra2 = "";

            if (f instanceof Villa) {

                extra1 = String.valueOf(((Villa) f).getFloor());
                extra2 = "Tiêu chuẩn: " + ((Villa) f).getStandard() + ", Hồ bơi: " + ((Villa) f).getPoolArea() + " m²";
            } else if (f instanceof House) {

                extra1 = String.valueOf(((House) f).getFloor());
                extra2 = "Tiêu chuẩn: " + ((House) f).getStandard();
            } else if (f instanceof Room) {

                extra1 = "-";
                extra2 = "DV miễn phí: " + ((Room) f).getFreeService();
            }

            System.out.printf("| %-8s | %-20s | %-8.2f | %-10.2f | %-10d | %-10s | %-10s | %-25s | %-10d |\n",
                    f.getFacilityID(), f.getNameService(), f.getUserArea(), f.getCost(),
                    f.getMaxPerson(), f.getType(),extra1, extra2, f.getTimesUsed());
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }



    @Override
    public void display(ArrayList<Facility> entities) {

    }

    @Override
    public Facility getDetail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choie Service you want:");
        System.out.println("1. Villa");
        System.out.println("2. House");
        System.out.println("3. Room");
        System.out.print("Lựa chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());
        String id = checkFaciID();
        String nameService = Validation.getValidServiceType("Input Service your want : House, Room, Villa","Invalid choice! try again!");
        double userArea = Utils.inputDouble("Input Area of service <= 30m2",1,30);
        double cost = Utils.inputDouble("Input Cost of service","Invalid input!");
        int maxPerson = Utils.inputInt("Input max person permit to rent any type of service :", 1,10);
        String type = Validation.getValidCRentType("Input Rent of Type : Ngay, thang, gio , nam","Invalid input");

        switch (choice) {
            case 1: // Villa

                String villaStandard =  Utils.getAString("Nhập tiêu chuẩn phòng: ", "Invalid input");


                double poolArea = Utils.inputDouble("Nhập diện tích hồ bơi: ", "Invalid");


                int villaFloor = Utils.inputInt("Nhap so tang cho tao :", 1,20);

                return new Villa(id, nameService, userArea, cost, maxPerson, type, villaStandard, poolArea, villaFloor,0);

            case 2: // Hous
                String houseStandard = Utils.getAString("Nhập tiêu chuẩn phòng: ", "Invalid input");


                int houseFloor = Utils.inputInt("Nhap so tang cho tao :", 1,20);

                return new House(id, nameService, userArea, cost, maxPerson, type, houseStandard, houseFloor,0);

            case 3: // Room

                String freeService = Utils.getAString("DIch vu Mien Phi :  ", "Invalid input");

                return new Room(id, nameService, userArea, cost, maxPerson, type, freeService,0);

            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return null;
        }
    }
    public String checkFaciID() {
        String faciID;
        while (true) {
            faciID = Utils.getAStringFormat("Input Facility_ID : ", "Facility ID must be in the correct form SVXX-0000", "^SV(VL|HO|RO)-\\d{4}$");
            if (facilityService.findById(faciID) == null) {
                break;
            } else {
                System.out.println("This facility ID  already exits, please input a new ID");
            }
        }
        return faciID;
    }
    public void displayFacilityMaintenance() {
        facilityService.displayMaintenanceMonth();
    }
}
