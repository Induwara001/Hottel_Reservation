package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class checkoutView extends JPanel { // JPanel එකක් විදියට හැදුවා Main Frame එකට දාන්න ලේසි වෙන්න

    public JTextField txtRoomNo = new JTextField("Room Number");
    public JLabel lblGuestName = new JLabel("-");
    public JLabel lblCheckIn = new JLabel("-");
    public JLabel lblCheckOut = new JLabel("-");
    public JLabel lblTotalAmount = new JLabel("0.00");

    public JButton btnSearch = new JButton("Search Bill");
    public JButton btnCheckout = new JButton("Confirm Check Out");
    public JButton btnClear = new JButton("Clear");

    public checkoutView() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);

        // Header Section
        JPanel header = new JPanel();
        header.setBackground(new Color(192, 57, 43)); // රතු පාට (Checkout වලට ගැලපෙනවා)
        JLabel title = new JLabel("Guest Check-Out & Billing");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- 1. Search Section ---
        JPanel searchPanel = createTitledPanel("Search Active Booking");
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;

        setupPlaceholder(txtRoomNo, "Room Number");
        searchPanel.add(new JLabel("Enter Room No: "), getGbc(0, 0));
        searchPanel.add(txtRoomNo, getGbc(1, 0));
        btnSearch.setBackground(new Color(52, 152, 219));
        btnSearch.setForeground(Color.WHITE);
        searchPanel.add(btnSearch, getGbc(2, 0));

        centerPanel.add(searchPanel, gbc);

        // --- 2. Bill Summary Section ---
        JPanel billPanel = createTitledPanel("Bill Summary");
        gbc.gridy = 1;

        JPanel detailsGrid = new JPanel(new GridLayout(4, 2, 10, 15));
        detailsGrid.setOpaque(false);

        detailsGrid.add(new JLabel("Guest Name:"));
        lblGuestName.setFont(new Font("SansSerif", Font.BOLD, 14));
        detailsGrid.add(lblGuestName);

        detailsGrid.add(new JLabel("Check-in Date:"));
        detailsGrid.add(lblCheckIn);

        detailsGrid.add(new JLabel("Check-out Date:"));
        detailsGrid.add(lblCheckOut);

        detailsGrid.add(new JLabel("Total Payable ($):"));
        lblTotalAmount.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTotalAmount.setForeground(new Color(192, 57, 43));
        detailsGrid.add(lblTotalAmount);

        billPanel.add(detailsGrid);
        centerPanel.add(billPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // --- Footer Section ---
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        btnCheckout.setBackground(new Color(39, 174, 96));
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCheckout.setPreferredSize(new Dimension(200, 40));

        footer.add(btnClear);
        footer.add(btnCheckout);
        add(footer, BorderLayout.SOUTH);
    }

    // Helper methods
    private JPanel createTitledPanel(String title) {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), title, TitledBorder.LEFT, TitledBorder.TOP));
        p.setBackground(Color.WHITE);
        p.setPadding(new Insets(15, 15, 15, 15)); // Custom padding
        return p;
    }

    private GridBagConstraints getGbc(int x, int y) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x; g.gridy = y;
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;
        if(x == 1) g.weightx = 1.0;
        return g;
    }

    private void setupPlaceholder(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) { field.setText(""); field.setForeground(Color.BLACK); }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) { field.setForeground(Color.GRAY); field.setText(placeholder); }
            }
        });
    }
}
