package view.pages;

import controller.MedicineController;
import model.Medicine;
import model.SaleItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MedicineSelector extends JFrame {
    private JLabel totalLabel;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color CARD_BG = new Color(248, 249, 250);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private List<SaleItem> selectedItems = new ArrayList<>();
    private List<MedicineCard> cards = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();
    JFrame parent;
    private MedicineController mc;

    public MedicineSelector(JFrame parent) {
        this.parent = parent;
        this.mc = new MedicineController();
        initPanel();
    }

    private void initPanel() {
        medicines = mc.getAllMedicines();
        if (!medicines.isEmpty()) {
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

            // display available medicines

            for (Medicine medicine : medicines) {
                MedicineCard card = new MedicineCard(
                        medicine.name(),
                        medicine.sellPrice(),
                        medicine.stock()
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
        } else {
            // no medicines message
            JPanel noMedicineMessage = new JPanel();
            noMedicineMessage.add(new JLabel("It seems like there is no medicines!!"));
            add(noMedicineMessage);
        }
        setSize(900, 650);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // update the sale total
    private void updateTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (MedicineCard card : cards) {
            if (card.isSelected()) {
                total = total.add(card.getLineTotal());
                selectedItems.add(new SaleItem(
                        1,
                        1,
                        total
                ));
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
        private BigDecimal price;

        public MedicineCard(String name, BigDecimal price, int stock) {
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
            BigDecimal total = price.multiply(BigDecimal.valueOf(qty));
            lineTotalLabel.setText(String.format("$%.2f", total));
        }

        public boolean isSelected() {
            return selectBox.isSelected();
        }

        public BigDecimal getLineTotal() {
            int qty = (int) quantitySpinner.getValue();
            return price.multiply(BigDecimal.valueOf(qty));
        }
    }
}
