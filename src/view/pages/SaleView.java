package view.pages;

import model.SaleItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class SaleView extends JFrame {
    List<SaleItem> items;
    JPanel parent;

    private final Color BG_COLOR = new Color(248, 249, 250);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color PRIMARY = new Color(13, 110, 253);
    private final Color TEXT_COLOR = new Color(33, 37, 41);

    public SaleView(JPanel parent, List<SaleItem> items) {
        this.parent = parent;
        this.items = items;
        this.setLayout(new BorderLayout(10, 10));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(BG_COLOR);
        initSaleView();
    }

    public void initSaleView() {

        // frame header
        JLabel header = new JLabel("Sale #1 — 12/12/2024 at 12:12 PM", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setForeground(TEXT_COLOR);
        header.setBorder(new EmptyBorder(15, 10, 15, 10));
        header.setOpaque(true);
        header.setBackground(Color.WHITE);

        // header content
        JPanel contentHeader = new JPanel(new BorderLayout());
        contentHeader.setBackground(Color.WHITE);
        contentHeader.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Sale Items");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(TEXT_COLOR);

        JButton exportButton = new JButton("Export");
        exportButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        exportButton.setBackground(PRIMARY);
        exportButton.setForeground(Color.WHITE);
        exportButton.setFocusPainted(false);
        exportButton.setBorder(new EmptyBorder(6, 14, 6, 14));
        exportButton.addActionListener(e -> export());
        contentHeader.add(title, BorderLayout.WEST);
        contentHeader.add(exportButton, BorderLayout.EAST);

        // items
        JPanel itemsPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        itemsPanel.setBackground(BG_COLOR);
        itemsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (SaleItem item : items) {
            itemsPanel.add(createItemCard(item));
        }

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        mainPanel.add(contentHeader, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(header, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setSize(720, 600);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public JPanel createItemCard(SaleItem item) {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 0));
        panel.setBackground(CARD_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(10, 10, 10, 10)
        ));

        panel.add(createLabel("Medicine Name: " + item.getName()));
        panel.add(createLabel("Quantity: " + item.getQuantity()));
        panel.add(createLabel("Unit Price: " + item.getPrice()));
        panel.add(createLabel("Total: " + item.getTotal()));

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    public void export() {
        JFileChooser chooser = new JFileChooser();
        int x = chooser.showSaveDialog(this);
        System.out.println("x: " + x);
        if (x == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getName();
            String path = chooser.getSelectedFile().getParentFile().getPath();
            System.out.println(path);
            String file = path + "\\" + name + ".xlsx";
            try{
                File newFile = new File(file);
                newFile.createNewFile();
                FileWriter fileWriter = new FileWriter(newFile);

                fileWriter.write("Name\tquantity\tprice(unit)\ttotal\t\n");

                for (SaleItem item : items) {
                    fileWriter.write(item.getName() + "\t" + item.getQuantity() + "\t" + item.getPrice() + "\t" + item.getTotal() + "\t\n");
                }

                fileWriter.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
