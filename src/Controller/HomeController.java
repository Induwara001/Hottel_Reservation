package Controller;

import Model.RegisterModel;
import View.HomeView;
import MainFrame.MainFrame;
import View.RegisterView;

public class HomeController {

    private HomeView view;

    public HomeController(HomeView view) {
        this.view = view;

        // 1. Handle "Register" Click
        this.view.addRegisterListener(e -> openRegisterModule());

        // 2. Handle "Room Booking" Click
        this.view.addBookingListener(e -> openBookingModule());
    }

    private void openRegisterModule() {
        // A. Initialize the MVC for Register
        RegisterModel model = new RegisterModel();
        RegisterView panel = new RegisterView();
        new RegisterController(panel, model); // Connect them

        // B. Load it into the Singleton MainFrame
        MainFrame.getInstance().switchPage(panel);
        MainFrame.getInstance().setVisible(true);

        // C. Close the Dashboard
        view.dispose();
    }

    private void openBookingModule() {
        // Example for Booking
        // BookingModel model = new BookingModel();
        // BookingPanel panel = new BookingPanel();
        // new BookingController(panel, model);
        // MainFrame.getInstance().switchPage(panel);
        // MainFrame.getInstance().setVisible(true);
        // view.dispose();
    }
}
