package Controller;

import Model.checkoutModel;
import View.checkoutView;
import javax.swing.JOptionPane;

public class checkoutController {
    private checkoutView view;

    public checkoutController(checkoutView view) {
        this.view = view;

        // --- Search Button Logic ---
        this.view.btnSearch.addActionListener(e -> {
            String roomNo = view.txtRoomNo.getText().trim();
            if (roomNo.isEmpty() || roomNo.equals("Room Number")) {
                JOptionPane.showMessageDialog(view, "Please enter a Room Number!");
                return;
            }

            String[] details = checkoutModel.getActiveBookingDetails(roomNo);
            if (details != null) {
                view.lblGuestName.setText(details[0]);
                view.lblCheckIn.setText(details[1]);
                view.lblCheckOut.setText(details[2]);
                view.lblTotalAmount.setText(details[3]);
                JOptionPane.showMessageDialog(view, "Booking details loaded!");
            } else {
                JOptionPane.showMessageDialog(view, "No active bookings found for this Room No!", "Error", JOptionPane.ERROR_MESSAGE);
                clearView();
            }
        });

        // --- Checkout Button Logic ---
        this.view.btnCheckout.addActionListener(e -> {
            String roomNo = view.txtRoomNo.getText().trim();
            if (view.lblGuestName.getText().equals("-")) {
                JOptionPane.showMessageDialog(view, "Please search for a booking first!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, "Confirm Checkout and Payment?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (checkoutModel.performCheckout(roomNo)) {
                    JOptionPane.showMessageDialog(view, "Checkout Successful! Room is now Available.");
                    clearView();
                } else {
                    JOptionPane.showMessageDialog(view, "Checkout Failed! Database error.");
                }
            }
        });

        // --- Clear Button ---
        this.view.btnClear.addActionListener(e -> clearView());
    }

    private void clearView() {
        view.txtRoomNo.setText("Room Number");
        view.lblGuestName.setText("-");
        view.lblCheckIn.setText("-");
        view.lblCheckOut.setText("-");
        view.lblTotalAmount.setText("0.00");
    }
}