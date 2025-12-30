package view;

import controller.MedicineController;
import view.common.Button;
import view.pages.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 * Pharmacy Management Dashboard
 * Author: Bilal Belhaj
 */
public class Dashboard extends JFrame {
    private JPanel leftBar;
    private JPanel rightBar;
    private JPanel mainPanel;
    private JPanel footer;
    public Dashboard() {
        this.setTitle("Pharmacy Management System");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(1400, 700);
        this.setLocationRelativeTo(null);

        // Search Bar
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchBar.setBackground(new Color(240, 240, 240));
        searchBar.setBorder(new EmptyBorder(5, 20, 5, 20));

        JTextField searchInput = new JTextField();
        searchInput.setColumns(40);
        searchInput.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchInput.setText("Search products, sales, suppliers...");
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 123, 255));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        searchBar.add(searchInput);
        searchBar.add(searchButton);

        // Left Sidebar
        leftBar = new JPanel();
        leftBar.setLayout(new GridLayout(8, 1, 5, 5));
        leftBar.setBackground(new Color(34, 49, 63));
        leftBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        Button productsButton = new Button("Medicines");
        productsButton.addActionListener(e -> showPanel(new ProductsPanel()));

        Button addProductButton = new Button("Add Medicine");
        addProductButton.addActionListener(e -> new AddMedicine(Dashboard.this));

        Button salesButton = new Button("Sales");
        salesButton.addActionListener(e -> showPanel(new SalesPanel()));

        Button purchasesButton = new Button("Purchases");
        purchasesButton.addActionListener(e -> showPanel(new PurchasesPanel()));

        Button suppliersButton = new Button("Suppliers");
        suppliersButton.addActionListener(e -> showPanel(new SuppliersPanel()));

        Button reportsButton = new Button("Reports");
        Button usersButton = new Button("Users");

        leftBar.add(productsButton);
        leftBar.add(addProductButton);
        leftBar.add(salesButton);
        leftBar.add(purchasesButton);
        leftBar.add(suppliersButton);
        leftBar.add(reportsButton);
        leftBar.add(usersButton);

        // Right Sidebar
        rightBar = new JPanel(new GridLayout(5,1,10,10));
        rightBar.setBackground(new Color(236, 240, 241));
        rightBar.setBorder(new EmptyBorder(10,10,10,10));

        // right sidebar buttons

        JButton saleButton = createButton("Quick Sale");
        saleButton.addActionListener(e -> new MedicineSelector((JFrame) Dashboard.this));

        JButton purchaseButton = createButton("Quick Purchase");
        JButton alertButton = createButton("Stock Alert");
        JButton expiredButton = createButton("Expired Medicines");
        JButton settingsButton = createButton("Settings");

        rightBar.add(saleButton);
        rightBar.add(purchaseButton);
        rightBar.add(alertButton);
        rightBar.add(expiredButton);
        rightBar.add(settingsButton);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new ProductsPanel());

        // Footer
        footer = new JPanel();
        footer.setBackground(new Color(34, 49, 63));
        JLabel footerLabel = new JLabel("Developed with ❤️ by Bilal Belhaj");
        footerLabel.setForeground(Color.WHITE);
        footer.setPreferredSize(new Dimension(getWidth(), 30));
        footer.add(footerLabel);

        // Add Panels to Frame
        add(searchBar, BorderLayout.NORTH);
        add(leftBar, BorderLayout.WEST);
        add(rightBar, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void showPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(41, 128, 185));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}