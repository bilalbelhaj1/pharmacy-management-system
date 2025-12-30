package view.pages;

import controller.MedicineController;
import model.Medicine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineSelector extends JFrame {

    private JLabel totalLabel;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color CARD_BG = new Color(248, 249, 250);
    private final Color TEXT_COLOR = new Color(44, 62, 80);

    private List<MedicineCard> cards = new ArrayList<>();

    JFrame parent;

    public MedicineSelector(JFrame parent) {
        this.parent = parent;
        initPanel();
    }

    private void initPanel() {
        setTitle("Select Medicines");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Header
        JLabel headerLabel = new JLabel("Select Medicines for Sale", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerLabel.setForeground(TEXT_COLOR);
        headerLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Cards Grid
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        //
        for (int i = 1; i <= 20; i++) {
            MedicineCard card = new MedicineCard(
                    "Medicine " + i,
                    10 + i,
                    50
            );
            cards.add(card);
            gridPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Action Buttons and total
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBorder(new EmptyBorder(10, 20, 10, 20));
        footer.setBackground(BACKGROUND_COLOR);

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalLabel.setForeground(new Color(40, 167, 69));

        JButton nextButton = new JButton("Confirm Sale");
        nextButton.setBackground(new Color(40, 167, 69));
        JButton cancelButton = new JButton("Cancel");
        nextButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        nextButton.setPreferredSize(new Dimension(140, 40));
        nextButton.setForeground(new Color(236, 240, 241));

        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        cancelButton.setPreferredSize(new Dimension(140, 40));
        cancelButton.addActionListener(e -> cancelSale());

        footer.add(totalLabel, BorderLayout.WEST);
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(nextButton);
        footer.add(buttonsPanel, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);

        setSize(900, 650);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // update the sale total
    private void updateTotal() {
        double total = 0;

        for (MedicineCard card : cards) {
            if (card.isSelected()) {
                total += card.getLineTotal();
            }
        }

        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    // cancel sale
    private void cancelSale() {
        this.dispose();
    }

    private class MedicineCard extends JPanel {

        private JCheckBox selectBox;
        private JSpinner quantitySpinner;
        private JLabel lineTotalLabel;
        private double price;

        public MedicineCard(String name, double price, int stock) {
            this.price = price;

            setLayout(new BorderLayout(10, 0));
            setBackground(CARD_BG);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220)),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            // left
            selectBox = new JCheckBox();
            selectBox.setOpaque(false);

            // center
            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setOpaque(false);

            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

            JLabel detailsLabel = new JLabel("$" + price + " • Stock: " + stock);
            detailsLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

            infoPanel.add(nameLabel);
            infoPanel.add(detailsLabel);

            // right
            JPanel rightPanel = new JPanel(new GridLayout(2, 1, 5, 5));
            rightPanel.setOpaque(false);

            quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, stock, 1));
            quantitySpinner.setEnabled(false);

            lineTotalLabel = new JLabel("$0.00", SwingConstants.RIGHT);
            lineTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 13));

            rightPanel.add(quantitySpinner);
            rightPanel.add(lineTotalLabel);

            add(selectBox, BorderLayout.WEST);
            add(infoPanel, BorderLayout.CENTER);
            add(rightPanel, BorderLayout.EAST);

            // event for checkbox
            selectBox.addActionListener(e -> {
                quantitySpinner.setEnabled(selectBox.isSelected());
                updateLineTotal();
                updateTotal();
            });

            // change event for spinner
            quantitySpinner.addChangeListener(e -> {
                updateLineTotal();
                updateTotal();
            });
        }

        private void updateLineTotal() {
            if (!selectBox.isSelected()) {
                lineTotalLabel.setText("$0.00");
                return;
            }
            int qty = (int) quantitySpinner.getValue();
            double total = qty * price;
            lineTotalLabel.setText(String.format("$%.2f", total));
        }

        public boolean isSelected() {
            return selectBox.isSelected();
        }

        public double getLineTotal() {
            int qty = (int) quantitySpinner.getValue();
            return qty * price;
        }
    }
}
