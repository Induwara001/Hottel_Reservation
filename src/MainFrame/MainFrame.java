package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private JPanel contentArea;
    // Class variables for buttons
    private JButton btnRegister, btnBooking, btnRooms, btnCancel, btnGuest, btnReports;

    private MainFrame() {
        setTitle("Hotel Plaza Workspace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // --- 1. BACKGROUND IMAGE FIX ---
        // Ensure "baxkground2.jpg" is in your 'src' folder. Case sensitive!
        BackgroundPanel bgPanel = new BackgroundPanel("/baxkground2.jpg");
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);

        // --- 2. SIDEBAR (Extended to Top) ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, 800));
        // Navy Blue with transparency (200) so background shows through slightly
        sidebar.setBackground(new Color(20, 33, 61, 200));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Add top spacing
        sidebar.add(Box.createRigidArea(new Dimension(0, 50)));

        // Initialize Buttons
        btnRegister = createSidebarBtn("Register");
        btnBooking = createSidebarBtn("Room Booking");
        btnRooms = createSidebarBtn("Rooms");
        btnCancel = createSidebarBtn("Cancel Booking");
        btnGuest = createSidebarBtn("Guests");
        btnReports = createSidebarBtn("Reports");

        // --- 3. ADD BUTTONS WITH BIGGER GAPS ---
        // I changed the gap from 10 to 30 for better spacing
        sidebar.add(btnRegister);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnBooking);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnRooms);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnCancel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnGuest);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnReports);

        add(sidebar, BorderLayout.WEST);



        contentArea = new JPanel(new BorderLayout());
        contentArea.setOpaque(false);
        add(contentArea, BorderLayout.CENTER);
    }

    public static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }

    public void switchPage(JPanel newPage) {
        contentArea.removeAll();


        newPage.setOpaque(false);

        contentArea.add(newPage, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
    }

    private JButton createSidebarBtn(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 50));
        btn.setBackground(new Color(40, 60, 100));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        return btn;
    }

    class BackgroundPanel extends JPanel {
        private Image img;
        public BackgroundPanel(String path) {
            try {
                img = new ImageIcon(getClass().getResource(path)).getImage();
            } catch (Exception e) {
                System.out.println("Background image not found: " + path);
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


    public void addRegisterListener(ActionListener listener)
    {
        btnRegister.addActionListener(listener);
    }
    public void addBookingListener(ActionListener listener)
    {
        btnBooking.addActionListener(listener);
    }
    public void addRoomsListener(ActionListener listener)
    {
        btnRooms.addActionListener(listener);
    }
    public void addCancelListener(ActionListener listener)
    {
        btnCancel.addActionListener(listener);
    }
    public void addGuestsListener(ActionListener listener)
    {
        btnGuest.addActionListener(listener);
    }
    public void addReportsListener(ActionListener listener)
    {
        btnReports.addActionListener(listener);
    }

}



