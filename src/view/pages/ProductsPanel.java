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
    JButton exportButton;
    JTable table;

    public ProductsPanel() {
        mc = new MedicineController();
        this.setLayout(new BorderLayout(10,10)); // add spacing
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setBackground(new Color(248,249,250)); // light gray background

        try {
            medicines = mc.getAllMedicines();
            String[] columns = {"ID", "name", "form", "Price", "Stock", "Expiration Date"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Medicine medicine : medicines) {
                model.addRow(new Object[]{
                        medicine.id(),
                        medicine.name(),
                        medicine.form(),
                        medicine.sellPrice(),
                        medicine.stock(),
                        medicine.expiration_date()
                });
            }

            // Header
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            headerPanel.setBackground(Color.WHITE);

            JLabel label = new JLabel("All Medicines");
            label.setFont(new Font("Segoe UI", Font.BOLD, 20));
            label.setForeground(new Color(33,37,41));

            // Buttons panel
            JPanel buttonsPanel = new JPanel(new GridLayout(1,3,10,0));
            buttonsPanel.setBackground(Color.WHITE);

            exportButton = new JButton("Export List");
            styleButton(exportButton, new Color(13,110,253));

            JButton updateButton = new JButton("Update");
            styleButton(updateButton, new Color(40,167,69));

            JButton deleteButton = new JButton("Delete");
            styleButton(deleteButton, new Color(220,53,69));

            exportButton.addActionListener(this);

            updateButton.addActionListener(e -> updateSelected());
            deleteButton.addActionListener(e -> deleteSelected(model));

            buttonsPanel.add(exportButton);
            buttonsPanel.add(updateButton);
            buttonsPanel.add(deleteButton);

            headerPanel.add(label, BorderLayout.WEST);
            headerPanel.add(buttonsPanel, BorderLayout.EAST);

            // Table
            table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            table.setFillsViewportHeight(true);
            table.setRowHeight(28);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JScrollPane pane = new JScrollPane(table);

            this.add(pane, BorderLayout.CENTER);
            this.add(headerPanel, BorderLayout.NORTH);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
    }

    private void updateSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }
        int id = (int) table.getValueAt(row, 0);
        new UpdateMedcine(this, id);
    }

    private void deleteSelected(DefaultTableModel model) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a medicine first");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this, "Delete this medicine?", "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) table.getValueAt(row, 0);
            try {
                mc.deleteMedicine(id);
                model.removeRow(row);
                JOptionPane.showMessageDialog(this, "Medicine deleted successfully");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Something went wrong");
            }
        }
    }

    public void export(JTable table, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        TableModel model = table.getModel();

        for (int i = 0; i < model.getColumnCount(); i++) {
            fileWriter.write(model.getColumnName(i) + "\t");
        }
        fileWriter.write("\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
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
                String file = path + "\\" + name + ".xlsx";
                try {
                    export(table, new File(file));
                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        }
    }
}
