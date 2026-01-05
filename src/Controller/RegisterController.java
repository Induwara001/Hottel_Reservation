package Controller;

import Model.RegisterModel;
import View.RegisterView;

public class RegisterController {
    private RegisterView view;
    private RegisterModel model;

    public RegisterController(RegisterView view, RegisterModel model) {
        this.view = view;
        this.model = model;


        this.view.addSaveListener(e -> saveGuest());
    }

    private void saveGuest() {
        String name = view.getNameInput();
        String mobile = view.getMobileInput();

        if (model.saveGuest(name, mobile)) {
            view.showMessage("Guest Registered Successfully!");
        } else {
            view.showMessage("Error Registering Guest.");
        }
    }
}