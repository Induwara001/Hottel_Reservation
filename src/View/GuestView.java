package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuestView extends JPanel {


    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;

    public GuestView() {
        setLayout(new BorderLayout());


        setOpaque(false);


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));


        topPanel.setOpaque(false);

        JLabel lbl = new JLabel("Search Guest (NIC):");
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setForeground(Color.blue);

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);

        topPanel.add(lbl);
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);


        String[] columns = {"Guest ID", "First Name", "Last Name","Phone Number", "Email","NIC","Address", "City","Nationality"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Search Results"));

        scrollPane.setOpaque(false);


        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }


    public String getSearchInput() {
        return searchField.getText().trim();
    }
    public void addSearchListener(ActionListener l) {
        searchButton.addActionListener(l);
    }
    public void clearTable() {
        tableModel.setRowCount(0);
    }
    public void addRow(Object[] row) {
        tableModel.addRow(row);
    }
    public void displayError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}


