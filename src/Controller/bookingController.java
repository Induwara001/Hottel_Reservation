package Controller;

import Model.bookingModel;
import View.bookingView;
import java.awt.event.ActionListener;
import javax.swing.*;

public class bookingController {
    private bookingView view;
    private bookingModel model;

    public bookingController(bookingView view, bookingModel model) {
        this.view = view;
        this.model = model;

        // --- පියවර 1: Radio Buttons වෙනස් කරන විට Rooms Load කිරීම ---
        ActionListener loader = e -> updateRoomCombo();
        view.rbEconomy.addActionListener(loader);
        view.rbNormal.addActionListener(loader);
        view.rbVIP.addActionListener(loader);
        view.rbSingle.addActionListener(loader);
        view.rbDouble.addActionListener(loader);
        view.rbTriple.addActionListener(loader);

        // මුල් වරට Form එක load වන විට පවතින selection එකට අනුව load කිරීම
        updateRoomCombo();

        // --- පියවර 2: Search Button Logic (Room No වලින්) ---
        view.btnSearch.addActionListener(e -> {
            String selected = (String) view.cmbRoomNo.getSelectedItem();
            if (selected == null || selected.isEmpty()) return;

            String[] data = bookingModel.findRoomByNumber(selected);
            if (data != null) {
                if (data[0].equalsIgnoreCase("Economy")) view.rbEconomy.setSelected(true);
                else if (data[0].equalsIgnoreCase("Normal")) view.rbNormal.setSelected(true);
                else view.rbVIP.setSelected(true);

                if (data[1].equalsIgnoreCase("Single")) view.rbSingle.setSelected(true);
                else if (data[1].equalsIgnoreCase("Double")) view.rbDouble.setSelected(true);
                else view.rbTriple.setSelected(true);

                JOptionPane.showMessageDialog(view, "Room Found!");
            } else {
                JOptionPane.showMessageDialog(view, "Room Not Found!");
            }
        });

        // --- පියවර 3: Submit Logic ---
        view.btnSubmit.addActionListener(e -> {
            if (validateFields()) {
                syncModel();
                view.showConfirmationPage(model.getFirstName()+" "+model.getLastName(),
                        model.getPhone(), model.getNic(), model.getRoomType(),
                        model.getRoomCapacity(), model.getCheckInDate(),
                        model.getCheckOutDate(), model.getRoomNo());

                if (model.saveReservation()) {
                    JOptionPane.showMessageDialog(view, "Saved!");
                    clearForm();
                }
            }
        });

        view.btnClear.addActionListener(e -> clearForm());
    }

    private void updateRoomCombo() {
        String type = getSelectedText(view.rbEconomy, view.rbNormal, view.rbVIP);
        String cap = getSelectedText(view.rbSingle, view.rbDouble, view.rbTriple);
        java.util.List<String> rooms = bookingModel.getAvailableRooms(type, cap);

        view.cmbRoomNo.removeAllItems();
        for (String r : rooms) view.cmbRoomNo.addItem(r);
        if (rooms.isEmpty()) view.cmbRoomNo.addItem("No Available");
    }

    private void syncModel() {
        model.setFirstName(view.txtFName.getText());
        model.setLastName(view.txtLName.getText());
        model.setPhone(view.txtPhone.getText());
        model.setNic(view.txtNICNo.getText());
        model.setRoomType(getSelectedText(view.rbEconomy, view.rbNormal, view.rbVIP));
        model.setRoomCapacity(getSelectedText(view.rbSingle, view.rbDouble, view.rbTriple));
        model.setRoomNo((String) view.cmbRoomNo.getSelectedItem());
        model.setCheckInDate(view.checkInField.getText());
        model.setCheckOutDate(view.checkOutField.getText());
    }

    private String getSelectedText(JRadioButton... btns) {
        for (JRadioButton b : btns) if (b.isSelected()) return b.getText();
        return "";
    }

    private boolean validateFields() {
        if (view.txtFName.getText().equals("First Name")) return false;
        if (view.cmbRoomNo.getSelectedItem().equals("No Available")) return false;
        return true;
    }

    private void clearForm() {
        view.txtFName.setText("First Name");
        // ... අනෙක් text fields reset කරන code එක මෙතනට දාන්න
        updateRoomCombo();
    }
}