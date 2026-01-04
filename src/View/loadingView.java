package View;
import Controller.loginController;
import Model.loginModel;

import javax.swing.*;
import java.awt.*;


public class loadingView extends JFrame {

    private JProgressBar progressBar;
    private JLabel label1;
    private JLabel imageLabel;

    public loadingView() {
        setTitle("LOADING PAGE");
        setUndecorated(true);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label1 = new JLabel("WELCOME TO HOTEL RESERVATION SYSTEM", SwingConstants.CENTER);
        label1.setBounds(0, 40, 450, 30);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Arial", Font.BOLD, 14));
        add(label1);

        try {
            java.net.URL imgURL = getClass().getResource("/hotellogo.png");
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(img));
                imageLabel.setBounds(125, 100, 200, 150);
                add(imageLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressBar = new JProgressBar();
        progressBar.setBounds(35, 300, 380, 20);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setForeground(new Color(212, 175, 55));
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setBorderPainted(false);
        add(progressBar);

        getContentPane().setBackground(Color.BLACK);
        setVisible(true);

        loadProgress();
    }

    private void loadProgress() {
        Timer timer = new Timer(40, e -> {
            int value = progressBar.getValue();
            if (value < 100) {
                progressBar.setValue(value + 1);
            } else {
                ((Timer) e.getSource()).stop();


                dispose();


                logingView view = new logingView();
                loginModel model = new loginModel();

                loginController controller= new loginController(view,model);

                view.setVisible(true);
            }
        });
        timer.start();
    }
}




