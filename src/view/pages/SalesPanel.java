package view.pages;

import controller.SaleController;
import model.SaleItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class SalesPanel extends JPanel {
    JTable table;
    DefaultTableModel model;
    private final SaleController sc;
    public SalesPanel() {
        this.sc = new SaleController();
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(248, 249, 250)); // light gray background
        initSalesPanel();
    }

    private void initSalesPanel() {
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        headerPanel.setBackground(Color.WHITE);

        // Filter panel (left)
        JPanel filterPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        filterPanel.setBackground(Color.WHITE);

        String[] filters = {"Today", "This Week", "This Month", "This Year"};
        JComboBox<String> filterCombo = new JComboBox<>(filters);
        styleComboBox(filterCombo);

        JLabel label = new JLabel("Today's Sales");
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(new Color(33, 37, 41));

        filterPanel.add(filterCombo);
        filterPanel.add(label);

        // Buttons panel (right)
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBackground(Color.WHITE);

        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> viewSale());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSale());

        styleButton(viewButton, new Color(13, 110, 253));
        styleButton(deleteButton, new Color(220, 53, 69));

        buttonsPanel.add(viewButton);
        buttonsPanel.add(deleteButton);

        headerPanel.add(filterPanel, BorderLayout.WEST);
        headerPanel.add(buttonsPanel, BorderLayout.EAST);

        // table
        String[] columns = {"ID", "Total Medicines", "Total(price)", "Date", "Status"};
        model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row,int column){ return false;}
        };

        for (int i = 0; i < 40; i++) {
            model.addRow(new Object[]{
                    i + 1,
                    "10",
                    "2000$",
                    "12-12-2025",
                    "PAID"
            });
        }

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        // main panel
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void deleteSale() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,"Please Select Sale to delete", "row not selected", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = (int)model.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure You want to delete this sale", "delete confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if(sc.deleteSale(id)) {
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(this, "Could not delete this sale", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private void viewSale() {
        // open a new frame contient the sale info with option to export (my next step)
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this,"Please Select Sale to View the sale details", "row not selected", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = (int) model.getValueAt(row, 0);
            List<SaleItem> list = sc.getSaleItems(id);

            for (SaleItem item: list) {
                System.out.println(item.getMedicineId());
                System.out.println(item.getQuantity());
                System.out.println(item.getPrice());
                System.out.println("******************************************");
            }
        }
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
    }
}
