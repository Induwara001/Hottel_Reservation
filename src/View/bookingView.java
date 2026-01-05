package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class bookingView extends JPanel {

    public JTextField txtFName = new JTextField("First Name");
    public JTextField txtLName = new JTextField("Last Name");
    public JTextField txtPhone = new JTextField("07xxxxxxxx");
    public JTextField txtEmail = new JTextField("example@mail.com");
    public JTextField txtaddress = new JTextField("Street Address");
    public JTextField txtCity = new JTextField("City");
    public JTextField txtNationality = new JTextField("Nationality");
    public JTextField txtNICNo = new JTextField("NIC or Passport No");

    public JTextField cardField = new JTextField("Card Number");
    public JTextField cvcField = new JTextField("CVC Code");

    // Room ID වෙනුවට ComboBox එකක් දැම්මා
    public JComboBox<String> cmbRoomNo = new JComboBox<>(new String[]{"Select Room"});
    public JTextField checkInField = new JTextField("Check-in Date");
    public JTextField checkOutField = new JTextField("Check-out Date");

    public JRadioButton rbVisa = new JRadioButton("Visa");
    public JRadioButton rbMaster = new JRadioButton("MasterCard");
    public JRadioButton rbAmex = new JRadioButton("Amex");

    public JRadioButton rbEconomy = new JRadioButton("Economy", true);
    public JRadioButton rbNormal = new JRadioButton("Normal");
    public JRadioButton rbVIP = new JRadioButton("VIP");
    public JRadioButton rbSingle = new JRadioButton("Single", true);
    public JRadioButton rbDouble = new JRadioButton("Double");
    public JRadioButton rbTriple = new JRadioButton("Triple");

    public JButton btnSearch = new JButton("Search");
    public JButton btnSubmit = new JButton("Submit");
    public JButton btnClear = new JButton("Clear");

    public bookingView() {
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(44, 62, 80));
        JLabel title = new JLabel("Room Booking Management");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        header.add(title);
        add(header, BorderLayout.NORTH);

        initPlaceholders();

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.fill = GridBagConstraints.BOTH;
        mainGbc.insets = new Insets(10, 10, 10, 10);
        mainGbc.weighty = 1.0;

        JPanel personalData = createTitledPanel("Personal Data");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        row = addSingleInputRow(personalData, "First Name", txtFName, "name", row, gbc);
        row = addSingleInputRow(personalData, "Last Name", txtLName, "name", row, gbc);
        row = addSingleInputRow(personalData, "Phone", txtPhone, "phone", row, gbc);
        row = addSingleInputRow(personalData, "Email", txtEmail, "email", row, gbc);
        row = addSimpleRow(personalData, "Address", txtaddress, row, gbc);
        row = addSimpleRow(personalData, "City", txtCity, row, gbc);
        row = addSimpleRow(personalData, "Nationality", txtNationality, row, gbc);
        row = addSingleInputRow(personalData, "NIC", txtNICNo, "nic", row, gbc);

        mainGbc.gridx = 0; mainGbc.weightx = 0.55;
        centerPanel.add(personalData, mainGbc);

        JPanel rightContainer = new JPanel(new GridLayout(2, 1, 0, 15));
        rightContainer.setOpaque(false);

        JPanel paymentPanel = createTitledPanel("Payment");
        GridBagConstraints pGbc = new GridBagConstraints();
        pGbc.insets = new Insets(5, 10, 5, 10);
        pGbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel cardOptionPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        ButtonGroup cardGroup = new ButtonGroup();
        cardOptionPanel.add(createVerticalCardOption(rbVisa, cardGroup, "💳 VISA", "Visa"));
        cardOptionPanel.add(createVerticalCardOption(rbMaster, cardGroup, "💳 MASTER", "MasterCard"));
        cardOptionPanel.add(createVerticalCardOption(rbAmex, cardGroup, "💳 AMEX", "Amex"));
        rbVisa.setSelected(true);

        pGbc.gridy = 0; pGbc.gridwidth = 2;
        paymentPanel.add(new JLabel("Select Card Type:"), pGbc);
        pGbc.gridy = 1;
        paymentPanel.add(cardOptionPanel, pGbc);

        pGbc.gridwidth = 1;
        int pRow = 2;
        pRow = addSingleInputRow(paymentPanel, "Card Number", cardField, "card", pRow, pGbc);
        pRow = addSingleInputRow(paymentPanel, "CVC code", cvcField, "cvc", pRow, pGbc);
        rightContainer.add(paymentPanel);

        JPanel roomPanel = createTitledPanel("Room Data");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(rbEconomy); typeGroup.add(rbNormal); typeGroup.add(rbVIP);
        JPanel typeP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typeP.add(rbEconomy); typeP.add(rbNormal); typeP.add(rbVIP);
        addLabelAndComp(roomPanel, "Room Type", typeP, 0);

        ButtonGroup capGroup = new ButtonGroup();
        capGroup.add(rbSingle); capGroup.add(rbDouble); capGroup.add(rbTriple);
        JPanel capP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        capP.add(rbSingle); capP.add(rbDouble); capP.add(rbTriple);
        addLabelAndComp(roomPanel, "Room Capacity", capP, 1);

        addLabelAndComp(roomPanel, "Check in date", createDatePicker(checkInField), 2);
        addLabelAndComp(roomPanel, "Check out date", createDatePicker(checkOutField), 3);

        // UI වෙනස් කළා: Room No ලේබලය සහ Dropdown එක
        JPanel searchRow = new JPanel(new BorderLayout(10, 0));
        searchRow.add(new JLabel("Room No : "), BorderLayout.WEST);
        cmbRoomNo.setEditable(true); // මෙතනදී type කරන්නත් පුළුවන්
        searchRow.add(cmbRoomNo, BorderLayout.CENTER);
        btnSearch.setBackground(new Color(135, 206, 250));
        searchRow.add(btnSearch, BorderLayout.EAST);

        GridBagConstraints sGbc = new GridBagConstraints();
        sGbc.gridx = 0; sGbc.gridy = 4; sGbc.gridwidth = 2; sGbc.fill = GridBagConstraints.HORIZONTAL;
        sGbc.insets = new Insets(15, 10, 10, 10);
        roomPanel.add(searchRow, sGbc);
        rightContainer.add(roomPanel);

        mainGbc.gridx = 1; mainGbc.weightx = 0.45;
        centerPanel.add(rightContainer, mainGbc);
        add(centerPanel, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnSubmit.setBackground(new Color(40, 167, 69));
        btnSubmit.setForeground(Color.WHITE);
        footer.add(btnClear); footer.add(btnSubmit);
        add(footer, BorderLayout.SOUTH);

        applyKeyConstraints();
    }

    private void initPlaceholders() {
        setupPlaceholder(txtFName, "First Name");
        setupPlaceholder(txtLName, "Last Name");
        setupPlaceholder(txtPhone, "07xxxxxxxx");
        setupPlaceholder(txtEmail, "example@mail.com");
        setupPlaceholder(txtaddress, "Street Address");
        setupPlaceholder(txtCity, "City");
        setupPlaceholder(txtNationality, "Nationality");
        setupPlaceholder(txtNICNo, "NIC or Passport No");
        setupPlaceholder(cardField, "Card Number");
        setupPlaceholder(cvcField, "CVC Code");
    }

    private void applyKeyConstraints() {
        makeNumericOnly(txtPhone);
        makeNumericOnly(cardField);
        makeNumericOnly(cvcField);
        makeAlphabeticOnly(txtFName);
        makeAlphabeticOnly(txtLName);
        makeAlphabeticOnly(txtCity);
        makeAlphabeticOnly(txtNationality);
    }

    public void showConfirmationPage(String name, String phone, String nic, String rType, String rCap, String checkIn, String checkOut, String roomNo) {
        try {
            java.time.LocalDate d1 = java.time.LocalDate.parse(checkIn);
            java.time.LocalDate d2 = java.time.LocalDate.parse(checkOut);
            long nights = java.time.temporal.ChronoUnit.DAYS.between(d1, d2);
            if (nights <= 0) nights = 1;

            double nightCost = (rType.equalsIgnoreCase("VIP")) ? 300.0 : (rType.equalsIgnoreCase("Normal") ? 200.0 : 150.0);
            double total = nights * nightCost;

            Window parentWindow = SwingUtilities.windowForComponent(this);
            JDialog dialog = new JDialog((Frame) parentWindow, "Booking Summary", true);
            dialog.setSize(450, 650);
            dialog.setLayout(new BorderLayout());

            String html = "<html><body style='padding:20px; font-family:sans-serif;'>" +
                    "<h2 style='color:#2C3E50;'>Booking Details</h2><hr>" +
                    "<b>Name:</b> " + name + "<br><b>NIC:</b> " + nic + "<br><br>" +
                    "<b style='color:red;'>Room No: " + roomNo + "</b><br>" +
                    "<b>Dates:</b> " + checkIn + " to " + checkOut + "<br>" +
                    "<h3>Total: $" + total + "</h3>" +
                    "</body></html>";

            dialog.add(new JScrollPane(new JLabel(html)), BorderLayout.CENTER);
            JButton btnConfirm = new JButton("Confirm Booking");
            btnConfirm.addActionListener(e -> dialog.dispose());
            dialog.add(btnConfirm, BorderLayout.SOUTH);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Check your dates!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupPlaceholder(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    private int addSingleInputRow(JPanel p, String label, JTextField f, String type, int row, GridBagConstraints gbc) {
        gbc.gridy = row++; gbc.gridx = 0; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        p.add(new JLabel(label), gbc);
        JLabel vLabel = new JLabel(" ");
        vLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        f.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) { validateInput(f, vLabel, type); }
        });
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel box = new JPanel(new BorderLayout());
        box.setOpaque(false); box.add(f, BorderLayout.CENTER); box.add(vLabel, BorderLayout.SOUTH);
        p.add(box, gbc);
        return row;
    }

    private void validateInput(JTextField field, JLabel label, String type) {
        String text = field.getText().trim();
        if (text.isEmpty() || field.getForeground().equals(Color.GRAY)) return;
        boolean valid = false;
        if (type.equals("phone")) valid = text.matches("0\\d{9}");
        else if (type.equals("email")) valid = text.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        else if (type.equals("nic")) valid = text.matches("^(\\d{9}[vVxX]|\\d{12})$");
        else if (type.equals("card")) valid = text.matches("\\d{16}");
        else if (type.equals("cvc")) valid = text.matches("\\d{3}");
        else valid = text.length() >= 2;
        label.setText(valid ? "✓ Valid" : "⚠ Invalid");
        label.setForeground(valid ? new Color(40, 167, 69) : Color.RED);
    }

    private JPanel createVerticalCardOption(JRadioButton rb, ButtonGroup group, String icon, String text) {
        group.add(rb); JPanel p = new JPanel(); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel(icon); lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        rb.setText(text); rb.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(lbl); p.add(rb); return p;
    }

    private JPanel createDatePicker(JTextField field) {
        JPanel p = new JPanel(new BorderLayout(5, 0));
        field.setEditable(false);
        JButton btn = new JButton("📅");
        btn.addActionListener(e -> {
            String val = JOptionPane.showInputDialog(this, "Enter Date (YYYY-MM-DD):", "2026-01-05");
            if(val != null) { field.setText(val); field.setForeground(Color.BLACK); }
        });
        p.add(field, BorderLayout.CENTER); p.add(btn, BorderLayout.EAST); return p;
    }

    private JPanel createTitledPanel(String title) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), title, TitledBorder.CENTER, TitledBorder.TOP));
        return p;
    }

    private int addSimpleRow(JPanel p, String label, JTextField f, int row, GridBagConstraints gbc) {
        gbc.gridy = row++; gbc.gridx = 0; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        p.add(new JLabel(label), gbc); gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(f, gbc); return row;
    }

    private void addLabelAndComp(JPanel p, String label, Component c, int row) {
        GridBagConstraints g = new GridBagConstraints(); g.insets = new Insets(5, 10, 5, 10);
        g.gridy = row; g.gridx = 0; g.anchor = GridBagConstraints.WEST;
        p.add(new JLabel(label), g); g.gridx = 1; g.weightx = 1.0; g.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, g);
    }

    private void makeAlphabeticOnly(JTextField field) {
        field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!(Character.isLetter(e.getKeyChar()) || e.getKeyChar() == ' ' || e.getKeyChar() == '\b')) e.consume();
            }
        });
    }

    private void makeNumericOnly(JTextField field) {
        field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '\b')) e.consume();
            }
        });
    }
}