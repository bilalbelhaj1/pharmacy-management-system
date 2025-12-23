package view.pages;

import controller.MedicineController;
import model.Medicine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class ProductsPanel extends JPanel implements ActionListener {
    private MedicineController mc;
    private List<Medicine> medicines;
    Button exportButton;
    JTable table;
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
            exportButton = new Button("Export List");
            exportButton.setBackground(Color.green);
            exportButton.setFont(new Font("Segoe UI", Font.PLAIN, 19));
            exportButton.setForeground(Color.WHITE);
            exportButton.addActionListener(this);
            headerPanel.add(label, BorderLayout.WEST);
            headerPanel.add(exportButton, BorderLayout.EAST);

            // Table
            table = new JTable(data, columns);
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

    public void export(JTable table, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        TableModel model = table.getModel();

        for (int i = 0; i < model.getColumnCount() - 1; i++) {
            fileWriter.write(model.getColumnName(i) + "\t");
        }
        fileWriter.write("\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j <  model.getColumnCount() - 1; j++) {
                fileWriter.write(model.getValueAt(i, j) + "\t");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exportButton) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                String name = fileChooser.getSelectedFile().getName();
                String path = fileChooser.getSelectedFile().getParentFile().getPath();
                String file = path + "\\" + name + "xlsx";
                try {
                    export(table, new File(file));
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }
    }
}
