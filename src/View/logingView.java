package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class logingView extends JFrame {

    JTextField userName = new JTextField(20);
    JPasswordField password = new JPasswordField(20);
    JButton login = new JButton("Login");

    public logingView() {
        setTitle("Plaza Hotel Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null);


        BackgroundPanel bgPanel = new BackgroundPanel("/login.jpg");
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel label = new JLabel("HOTEL", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 32));
        gbc.gridy = 0;
        bgPanel.add(label, gbc);


        JLabel userLabel = new JLabel("Enter Username:");
        userLabel.setForeground(new Color(212, 175, 55));
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 1;
        bgPanel.add(userLabel, gbc);

        gbc.gridy = 2;
        bgPanel.add(userName, gbc);


        JLabel passLabel = new JLabel("Enter Password:");
        passLabel.setForeground(new Color(212, 175, 55));
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 3;
        bgPanel.add(passLabel, gbc);

        gbc.gridy = 4;
        bgPanel.add(password, gbc);


        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        login.setPreferredSize(new Dimension(120, 40));
        login.setBackground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 14));
        bgPanel.add(login, gbc);
    }


    class BackgroundPanel extends JPanel {
        private Image img;
        public BackgroundPanel(String path) {
            try {
                img = new ImageIcon(getClass().getResource(path)).getImage();
            } catch (Exception e) {
                System.out.println("Login background image failed to load.");
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {

                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public String getUserName() {
        return userName.getText();
    }
    public String getPassword() {
        return new String(password.getPassword());
    }
    public void loginListener(ActionListener listener) {
        login.addActionListener(listener);
    }
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }


    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }





}
