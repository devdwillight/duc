package controller;

import model.Booking;
import model.Contract;
import service.*;
import utils.CustomException;
import utils.InputvalidationException;
import view.*;

public class ResortController extends menu<String> {
    private EmployeeService employeeService = new EmployeeService();
    private CustomerService customerService = new CustomerService();
    private FacilityService facilityService = new FacilityService();
    private BookingService bookingService = new BookingService();
    private ContractService contractService = new ContractService();

    private FacilityView facilityView = new FacilityView();
    private CustomerView customerView = new CustomerView();
    private EmployeeView employeeView = new EmployeeView();
    private BookingView bookingView = new BookingView();
    private ContractView contractView = new ContractView();
    private PromotionView promotionView = new PromotionView();

    public ResortController(String td, String[] mc) {
        super(td, mc);
    }

    public void employeeManagement() throws InputvalidationException, CustomException {
        String[] choice = {"Display list employees", "Add new employee", "Edit employee\n4.Return main menu"};
        menu<String> employ = new menu<String>("Employee Management", choice) {
            @Override
            public void execute(int ch) throws InputvalidationException {

                switch (ch) {
                    case 1:
                        employeeView.display(employeeService.findAll());
                        break;
                    case 2:
                        employeeService.add(employeeView.getDetail());
                        break;
                    case 3:
                        employeeService.update(employeeView.updateEmployee());
                        break;
                    case 4:
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            }
        };
        employ.run1();
    }

    public void customerManagement() throws InputvalidationException, CustomException {
        String[] choice = {"Display list customers", "Add new customer", "Edit new customer\n4.Return main menu"};
        menu<String> customer = new menu<String>("Customer Management", choice) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1:
                        customerView.display(customerService.findAll());
                        break;
                    case 2:
                        customerService.add(customerView.getDetail());
                        break;
                    case 3:
                        customerService.update(customerView.updateCustomer());
                        break;
                    case 4:
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            }

        };
        customer.run1();
    }

    public void facilityManagement() throws InputvalidationException, CustomException {
        String[] choice = {"Display list facility", "Add new facility", "Display list facility maintenance\n4.Return main menu"};
        menu<String> facility = new menu<String>(" Facility Management", choice) {
            @Override
            public void execute(int ch) throws CustomException {
                switch (ch) {
                    case 1:
                        facilityView.display(facilityService.findAll());
                        break;
                    case 2:
                        facilityService.add(facilityView.getDetail());
                        break;
                    case 3:
                        facilityService.displayMaintenance();
                        break;
                    case 4:
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            }
        };
        facility.run();
    }

    public void createContractFlow() {
        Booking booking = bookingService.getNextBooking();
        if (booking == null) {
            System.out.println("Không còn booking nào.");
            return;
        }
        Contract contract = bookingView.inputContract(booking);
        bookingService.saveContract(contract);
    }

    public void bookingManagement() throws InputvalidationException, CustomException {
        String[] choice = {"Add new booking", "Display list booking", "Create new contracts", "Display List contracts", "Regular maintenance", "Edit contracts\n7.Return main menu"};
        menu<String> booking = new menu<String>("Booking Management", choice) {
            @Override
            public void execute(int ch) throws CustomException {
                switch (ch) {
                    case 1:
                        bookingService.add(bookingView.getDetail());
                        break;
                    case 2:
                        bookingView.display(bookingService.findAll());
                        break;
                    case 3:
                        createContractFlow();
                        break;
                    case 4:
                        contractView.display(contractService.findAll());
                        break;
                    case 5:
                        facilityView.displayFacilityMaintenance();
                        break;
                    case 6:
                        contractView.editContract();
                        break;
                    case 7:
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }

            }
        };
        booking.run3();
    }

    public void promotionManagement() throws InputvalidationException, CustomException {
        String[] choice = {"Display list customers use service", "Display list customers get voucher\n3.Return main menu"};
        menu<String> promotion = new menu<String>("Promotion Management", choice) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1:
                        promotionView.displayCustomersUsedServiceByYear();
                        break;
                    case 2:
                        promotionView.displayCustomersGetVoucher();
                        break;
                    case 3:
                        System.out.println("Quay lại menu chính.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            }
        };
        promotion.run();
    }

    @Override
    public void execute(int ch) throws InputvalidationException, CustomException {
        switch (ch) {
            case 1:
                employeeManagement();
                break;
            case 2:
                customerManagement();
                break;
            case 3:
                facilityManagement();
                break;
            case 4:
                bookingManagement();
                break;
            case 5:
                promotionManagement();
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) throws InputvalidationException, CustomException {
        String[] choie = {"Employee Management", "Customer Management", "Facility Management", "BookingManagement", "Promotion Management\n6.Exit"};
        ResortController resortController = new ResortController("Resort", choie);
        resortController.run();
    }


}

