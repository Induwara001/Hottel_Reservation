package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReportsView extends JPanel {

    private JComboBox<String> comboMonth;
    private JComboBox<String> comboYear;
    private JButton btnGenerate;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private JButton btnPrint;

    public ReportsView() {
        setLayout(new BorderLayout());
        setOpaque(false);


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        topPanel.setOpaque(false);

        topPanel.add(new JLabel("Month:"));
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        comboMonth = new JComboBox<>(months);
        topPanel.add(comboMonth);

        topPanel.add(new JLabel("Year:"));
        String[] years = {"2024", "2025", "2026"};
        comboYear = new JComboBox<>(years);
        topPanel.add(comboYear);

        btnGenerate = new JButton("Generate Report");
        btnGenerate.setBackground(new Color(0, 102, 204));
        btnGenerate.setForeground(Color.WHITE);
        topPanel.add(btnGenerate);

        btnPrint = new JButton("Print PDF");
        btnPrint.setBackground(new Color(0, 153, 76));
        btnPrint.setForeground(Color.WHITE);
        topPanel.add(btnPrint);

        add(topPanel, BorderLayout.NORTH);


        String[] columns = {"Booking ID", "Guest ID", "Guest Name", "Check In", "Check Out", "Payment Type"};

        tableModel = new DefaultTableModel(columns, 0);
        reportTable = new JTable(tableModel);
        reportTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(reportTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Monthly Booking Report"));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    public int getSelectedMonth() {
        return comboMonth.getSelectedIndex() + 1;
    }

    public int getSelectedYear() {
        return Integer.parseInt((String) comboYear.getSelectedItem());
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addRow(Object[] row) {
        tableModel.addRow(row);
    }

    public void addGenerateListener(ActionListener l) {
        btnGenerate.addActionListener(l);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void addPrintListener(ActionListener l) {
        btnPrint.addActionListener(l);
    }
    public JTable getTable() {
        return reportTable;
    }

}

