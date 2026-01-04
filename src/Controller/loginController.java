package Controller;

import Model.loginModel;
import View.HomeView;
import View.logingView;

import javax.swing.*;

public class loginController {
    private logingView view;
    private loginModel model;


    public loginController(logingView view, loginModel model) {
        this.view = view;
        this.model = model;


        this.view.loginListener(e -> handleLogin());
    }

    private void handleLogin() {
        String user = view.getUserName();
        String pass = view.getPassword();


        if (user.isEmpty() || pass.isEmpty()) {
            view.displayErrorMessage("Username and Password cannot be empty!");
            return;
        }

        try {

            if (model.checkCredentials(user, pass)) {

                view.displaySuccessMessage("Welcome to Plaza Hotel!");
                view.setVisible(false);

                HomeView home = new HomeView();
                new HomeController(home);
                home.setVisible(true);
            } else {

                view.displayErrorMessage("Invalid Username or Password.");
            }
        } catch (Exception ex) {

            view.displayErrorMessage("Database Error: " + ex.getMessage());
        }
    }
}
