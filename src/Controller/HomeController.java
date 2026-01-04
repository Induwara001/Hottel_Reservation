package Controller;

import Model.GuestModel;
import Model.RegisterModel;
import Model.ReportsModel;
import View.GuestView;
import View.HomeView;
import MainFrame.MainFrame;
import View.RegisterView;
import View.ReportsView;

public class HomeController {

    private HomeView view;

    public HomeController(HomeView view) {
        this.view = view;


        this.view.addRegisterListener(e -> openRegisterModule());

        this.view.addBookingListener(e -> openBookingModule());

        this.view.addGuestlListener(e-> openGuestModule());

        this.view.addReportsListener(e->openReportsModul());
    }

    private void openRegisterModule() {

        RegisterModel model = new RegisterModel();
        RegisterView Rview = new RegisterView();
       RegisterController controller= new RegisterController(Rview, model);

        MainFrame.getInstance().switchPage(Rview);
        MainFrame.getInstance().setVisible(true);


        view.dispose();
    }

    private void openBookingModule() {

    }


    private void openGuestModule(){
        GuestModel model=new GuestModel();
        GuestView Gview=new GuestView();
        GuestController controller=new GuestController(Gview,model);
        MainFrame.getInstance().switchPage(Gview);
        MainFrame.getInstance().setVisible(true);

        view.dispose();

    }

   private void openReportsModul(){
        ReportsModel model=new ReportsModel();
       ReportsView Rview=new ReportsView();
       ReportsController controller=new ReportsController(Rview,model);
       MainFrame.getInstance().switchPage(Rview);
       MainFrame.getInstance().setVisible(true);
   }
}
