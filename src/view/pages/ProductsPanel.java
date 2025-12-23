package view.pages;

import controller.MedicineController;
import model.Medicine;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class ProductsPanel extends JPanel {
    private MedicineController mc;
    private List<Medicine> medicines;
    public ProductsPanel() {
        mc = new MedicineController();
        this.setLayout(new BorderLayout());
        try {
            medicines = mc.getAllMedicines();
            String[] columns = {"ID", "name", "form", "Price", "Stock", "Expiration Date", "Actions"};
            Object[][] data = new Object[medicines.size()][7];

            int i =0;
            for (Medicine medicine : medicines) {
                data[i][0] = medicine.id() + "";
                data[i][1] = medicine.name();
                data[i][2] = medicine.form();
                data[i][3] = medicine.sellePrice() + "";
                data[i][4] = medicine.stock() + "";
                data[i][5] = medicine.expiration_date() + "";
                data[i][6] = new Button("Hello");
                i++;
            }
            // Header
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            JLabel label = new JLabel("All Medicines");
            JButton exportButton = new JButton("Export List");
            headerPanel.add(label, BorderLayout.WEST);
            headerPanel.add(exportButton, BorderLayout.EAST);
            // Table
            JTable table = new JTable(data, columns);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            table.setFillsViewportHeight(true);
            JScrollPane pane = new JScrollPane(table);
            this.add(pane, BorderLayout.CENTER);
            this.add(headerPanel, BorderLayout.NORTH);
            this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

}
