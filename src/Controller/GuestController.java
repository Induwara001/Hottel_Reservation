package Controller;

import Model.GuestModel;
import View.GuestView;

import java.util.List;

public class GuestController {
    private GuestView view;
    private GuestModel model;

    public GuestController(GuestView view, GuestModel model) {
        this.view = view;
        this.model = model;


        this.view.addSearchListener(e -> performSearch());
    }

    private void performSearch() {
        String name = view.getSearchInput();


        if (name.isEmpty()) {
            view.displayError("Search field cannot be empty!");
            return;
        }


        view.clearTable();


        List<Object[]> results = model.searchGuests(name);


        if (results.isEmpty()) {
            view.displayError("No guests found matching: " + name);
        } else {

            for (Object[] row : results) {
                view.addRow(row);
            }
        }
    }
}
