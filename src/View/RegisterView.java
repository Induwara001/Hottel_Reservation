package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JPanel {
    private JTextField nameField = new JTextField(20);
    private JTextField mobileField = new JTextField(20);
    private JButton saveButton = new JButton("Save User");

    public RegisterView() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;


        JLabel title = new JLabel("Guest Registration");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        add(title, gbc);


        gbc.gridwidth = 1; gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Mobile:"), gbc);
        gbc.gridx = 1;
        add(mobileField, gbc);


        gbc.gridx = 1; gbc.gridy = 3;
        add(saveButton, gbc);
    }

    public String getNameInput() {
        return nameField.getText();
    }
    public String getMobileInput() {
        return mobileField.getText();
    }
    public void addSaveListener(ActionListener l) {
        saveButton.addActionListener(l);
    }
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}

