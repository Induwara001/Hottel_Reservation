package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    // 1. Declare Sidebar Buttons as Class Variables (so Controller can use them)
    private JButton btnCheckIn;
    private JButton btnCheckOut;
    private JButton btnBooking;
    private JButton btnCancel;
    private JButton btnRooms;
    private JButton btnGuests;

    private static MainFrame instance;
    private JPanel contentArea;

    // Private Constructor (Singleton)
    private MainFrame() {
        setTitle("Hotel Plaza Workspace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- SIDEBAR PANEL ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, 800));
        sidebar.setBackground(new Color(20, 33, 61)); // Navy Blue
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); // Vertical stacking

        // Add spacing at top
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- 2. Initialize Buttons Inside the Class ---
        btnCheckIn = createSidebarBtn("Check In");
        btnCheckOut = createSidebarBtn("Check Out");
        btnBooking = createSidebarBtn("Room Booking");
        btnCancel = createSidebarBtn("Cancel Booking");
        btnRooms = createSidebarBtn("Rooms");
        btnGuests = createSidebarBtn("Guests");

        // --- 3. Add Buttons to Sidebar ---
        sidebar.add(btnCheckIn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10))); // Gap
        sidebar.add(btnCheckOut);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnBooking);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnCancel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnRooms);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnGuests);

        add(sidebar, BorderLayout.WEST);

        // --- HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(1200, 60));
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel title = new JLabel("  Hotel Plaza System", SwingConstants.LEFT);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(20, 33, 61));
        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        // --- CONTENT AREA ---
        contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(new Color(245, 245, 245));
        add(contentArea, BorderLayout.CENTER);
    }

    // Singleton Access
    public static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }

    // Method to switch center pages
    public void switchPage(JPanel newPage) {
        contentArea.removeAll();
        contentArea.add(newPage, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
    }

    // --- Helper to Design Buttons ---
    private JButton createSidebarBtn(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 50)); // Fill width
        btn.setBackground(new Color(40, 60, 100));  // Lighter blue for button
        btn.setForeground(Color.WHITE);             // White text
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT); // Left align text
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // Padding inside
        return btn;
    }

    // --- 4. Listener Methods (So you can make them click!) ---
    public void addCheckInListener(ActionListener l) { btnCheckIn.addActionListener(l); }
    public void addCheckOutListener(ActionListener l) { btnCheckOut.addActionListener(l); }
    public void addBookingListener(ActionListener l) { btnBooking.addActionListener(l); }
    public void addRoomsListener(ActionListener l) { btnRooms.addActionListener(l); }
    public void addGuestsListener(ActionListener l) { btnGuests.addActionListener(l); }
}

