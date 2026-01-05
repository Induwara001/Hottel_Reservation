package Controller;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import Model.ReportsModel;
import View.ReportsView;

import javax.swing.*;
import java.util.List;

    public class ReportsController {

        private ReportsView view;
        private ReportsModel model;

        public ReportsController(ReportsView view, ReportsModel model) {
            this.view = view;
            this.model = model;

            this.view.addGenerateListener(e -> generateReport());

            this.view.addPrintListener(e -> exportToPDF());
        }

        private void generateReport() {
            int month = view.getSelectedMonth();
            int year = view.getSelectedYear();

            view.clearTable();


            List<Object[]> reportData = model.getFullReport(month, year);

            if (reportData.isEmpty()) {
                view.showMessage("No bookings found for " + month + "/" + year);
            } else {
                for (Object[] row : reportData) {
                    view.addRow(row);
                }
            }
        }

        private void exportToPDF() {
            String path = "Monthly_Booking_Report.pdf";
            JTable table = view.getTable();

            try {

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();


                Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph title = new Paragraph("Hotel Plaza - Monthly Report", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);
                document.add(new Paragraph(" "));


                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());


                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i)));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }


                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        String cellValue = table.getValueAt(rows, cols).toString();
                        pdfTable.addCell(cellValue);
                    }
                }

                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(null, "PDF Saved Successfully!\nSaved to: " + path);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error creating PDF: " + e.getMessage());
            }
        }
    }

