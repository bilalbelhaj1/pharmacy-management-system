package view.pages;

import controller.PurchaseController;
import model.Purchase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class PurchasesPanel extends JPanel {
    private JTable table;
    private final PurchaseController pc;
    public PurchasesPanel() {
        this.pc = new PurchaseController();
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(248, 249, 250));
        initPurchasePanel();
    }
    private void initPurchasePanel() {

        List<Purchase> list = pc.getAll();
        if (!list.isEmpty()) {
            for (Purchase purchase : list) {
                System.out.println(purchase);
            }
        }

         // header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        headerPanel.setBackground(Color.WHITE);

        // Filter panel
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

        // buttons
        JButton viewButton = new JButton("View");
        JButton deleteButton = new JButton("Delete");

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2, 10,5));
        buttonsPanel.add(viewButton);
        buttonsPanel.add(deleteButton);

        styleButton(viewButton, new Color(13, 110, 253));
        styleButton(deleteButton, new Color(220, 53, 69));

        headerPanel.add(filterPanel, BorderLayout.WEST);
        headerPanel.add(buttonsPanel, BorderLayout.EAST);

        // main table
        String[] columns = {"#ID", "name", "date", "total", "something", "cool"};
        String[][] data = new String[30][6];

        for (int i = 0 ; i < 30; i++) {
            data[i][0] = "1";
            data[i][1] = "bilal";
            data[i][2] = "12-12-2999";
            data[i][3] = "2333";
            data[i][4] = "yes";
            data[i][5] = "No";
        }
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane pane = new JScrollPane(table);
        pane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        JPanel tablePanel = new JPanel();
        tablePanel.add(pane);

        this.add(pane, BorderLayout.CENTER);
        this.add(headerPanel, BorderLayout.NORTH);
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
