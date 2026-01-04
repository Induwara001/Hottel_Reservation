package Controller;

import Model.RegisterModel;
import View.HomeView;
import MainFrame.MainFrame;
import View.RegisterView;

public class HomeController {

    private HomeView view;

    public HomeController(HomeView view) {
        this.view = view;


        this.view.addRegisterListener(e -> openRegisterModule());


        this.view.addBookingListener(e -> openBookingModule());
    }

    private void openRegisterModule() {

        RegisterModel model = new RegisterModel();
        RegisterView panel = new RegisterView();
        new RegisterController(panel, model); // Connect them

        MainFrame.getInstance().switchPage(panel);
        MainFrame.getInstance().setVisible(true);


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
