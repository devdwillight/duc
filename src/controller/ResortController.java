package controller;

import service.CustomerService;
import service.EmployeeService;
import utils.InputvalidationException;
import view.CustomerView;
import view.EmployeeView;
import view.menu;

public class ResortController extends menu<String> {
    public EmployeeService employeeService = new EmployeeService();
    public CustomerService customerService = new CustomerService();
    public CustomerView customerView = new CustomerView();
    public EmployeeView employeeView = new EmployeeView();

    public ResortController(String td, String[] mc) {
        super(td, mc);
    }

    public void employeeManagement() throws InputvalidationException {
        String[] choice = {"Display list employees", "Add new employee", "Edit employee\n4.Return main menu"};
        menu<String> employ = new menu<String>("Employee Management", choice) {
            @Override
            public void execute(int ch) throws InputvalidationException {

                switch (ch) {
                    case 1:
                        employeeService.displayAllEmployees();
                        break;
                    case 2:

                        break;
                    case 3:
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

    public void customerManagement() throws InputvalidationException {
        String[] choice = {"Display list customers", "Add new customer", "Edit new customer\n4.Return main menu"};
        menu<String> customer = new menu<String>("Customer Management", choice) {
            @Override
            public void execute(int ch) {
                switch (ch) {
                    case 1:
                        customerView.display(customerService.findAll());
                        break;
                    case 2:
                        customerService.addNewCustomer(customerView.getDetail());
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

    public void facilityManagement() throws InputvalidationException {
        String[] choice = {"Display list facility", "Add new facility", "Edit new customer\n4.Return main menu"};
        menu<String> facility = new menu<String>("Customer Management", choice) {
            @Override
            public void execute(int ch) {
            }
        };
        facility.run();
    }

    public void bookingManagement() throws InputvalidationException {
        String[] choice = {"Add new booking ", "Display list booking", "Create new contracts", "Display List contracts", "Edit contracts\n6.Return main menu"};
        menu<String> booking = new menu<String>("Booking Management", choice) {
            @Override
            public void execute(int ch) {

            }
        };
        booking.run();
    }

    public void promotionManagement() throws InputvalidationException {
        String[] choice = {"Display list customers use service", "Display list customers get voucher\n3.Return main menu"};
        menu<String> promotion = new menu<String>("Promotion Management", choice) {
            @Override
            public void execute(int ch) {

            }
        };
        promotion.run();
    }

    @Override
    public void execute(int ch) throws InputvalidationException {
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

    public static void main(String[] args) throws InputvalidationException {
        String[] choie = {"Employee Management", "Customer Management", "Facility Management", "BookingManagement", "Promotion Management\n6.Exit"};
        ResortController resortController = new ResortController("Resort", choie);
        resortController.run();
    }


}

