package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {


    private JButton btnRegister;
    private JButton btnBooking;
    private JButton btnRooms;
    private JButton btnCheckOut;
    private JButton btnGuest;
    private JButton btnReports;

    public HomeView() {
        setTitle("Hotel Plaza - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(20, 33, 61));
        header.setPreferredSize(new Dimension(1000, 60));




        BackgroundPanel mainPanel = new BackgroundPanel("/background.jpg");
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;


        JLabel banner = new JLabel("Home Page", SwingConstants.CENTER);
        banner.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 36));
        banner.setOpaque(true);
        banner.setBackground(new Color(173, 216, 230, 200));
        banner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        banner.setPreferredSize(new Dimension(450, 70));
        gbc.gridy = 0;
        mainPanel.add(banner, gbc);


        JPanel buttonGrid = new JPanel(new GridLayout(2, 3, 40, 40));
        buttonGrid.setOpaque(false);

        btnRegister = createMenuButton("Register", "/add-user.png");
        btnBooking = createMenuButton("Room Booking", "/booking.png");
        btnRooms = createMenuButton("Rooms", "/room.png");
        btnCheckOut = createMenuButton("Check Out", "/booking.png");
        btnGuest = createMenuButton("Guest", "/guest.png");
        btnReports = createMenuButton("Reports", "/report.png");

        buttonGrid.add(btnRegister);
        buttonGrid.add(btnBooking);
        buttonGrid.add(btnRooms);
        buttonGrid.add(btnCheckOut);
        buttonGrid.add(btnGuest);
        buttonGrid.add(btnReports);

        gbc.gridy = 1;
        gbc.weighty = 0.8;
        mainPanel.add(buttonGrid, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text, String imagePath) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(200, 160));
        btn.setBackground(new Color(135, 206, 250));
        btn.setFont(new Font("Arial", Font.BOLD, 16));

        try {
            java.net.URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
        }

        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        return btn;
    }


    public void addRegisterListener(ActionListener listener) {
        btnRegister.addActionListener(listener);
    }

    public void addBookingListener(ActionListener listener) {
        btnBooking.addActionListener(listener);
    }

    public void addRoomsListener(ActionListener listener) {
        btnRooms.addActionListener(listener);
    }
    public void addCheckOutListener(ActionListener listener){
        btnCheckOut.addActionListener(listener);
    }
    public  void addGuestlListener(ActionListener listener)
    {
        btnGuest.addActionListener(listener);
    }
    public void addReportsListener(ActionListener listener)
    {
        btnReports.addActionListener(listener);
    }


    class BackgroundPanel extends JPanel {
        private Image img;
        public BackgroundPanel(String path) {
            try {
                img = new ImageIcon(getClass().getResource(path)).getImage();
            } catch (Exception e) {
                System.out.println("Background image failed to load.");
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
}

