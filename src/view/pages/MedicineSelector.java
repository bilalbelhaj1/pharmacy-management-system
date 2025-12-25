package view.pages;

import controller.MedicineController;
import model.Medicine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MedicineSelector extends JFrame {
    private JButton nextButton;
    private JButton cancelButton;
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color CARD_BG = new Color(248, 249, 250);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    private List<Medicine> medicineList;
    private MedicineController mc;
    JFrame parent;
    public MedicineSelector(JFrame parent) {
        this.parent = parent;
        initPanel();
    }

    private void initPanel() {
        this.mc = new MedicineController();
        this.medicineList = mc.getAllMedicines();
        System.out.println(this.medicineList);
        if (!this.medicineList.isEmpty()) {
            setTitle("Inventory Selection");
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.getContentPane().setBackground(BACKGROUND_COLOR);
            this.setLayout(new BorderLayout());

            // Header
            JLabel headerLabel = new JLabel("Select Medicines for Sale", SwingConstants.CENTER);
            headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
            headerLabel.setForeground(TEXT_COLOR);
            headerLabel.setBorder(new EmptyBorder(25, 0, 15, 0));
            this.add(headerLabel, BorderLayout.NORTH);


            // medicines grid
            JPanel gridPanel = new JPanel(new GridLayout(0, 3, 15, 15));
            gridPanel.setBackground(BACKGROUND_COLOR);
            gridPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

            for (int i = 0; i < 40; i++) {
                gridPanel.add(new MedicineCard("Medicine " + i, 2000.0, 23));
            }

            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            this.add(scrollPane, BorderLayout.CENTER);

            // Buttons
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
            buttonsPanel.setBackground(BACKGROUND_COLOR);

            cancelButton = styleButton("Cancel", Color.WHITE, Color.DARK_GRAY);
            nextButton = styleButton("Next Step", new Color(40, 167, 69), Color.WHITE);

            cancelButton.setPreferredSize(new Dimension(130, 45));
            nextButton.setPreferredSize(new Dimension(130, 45));

            buttonsPanel.add(cancelButton);
            buttonsPanel.add(nextButton);
            this.add(buttonsPanel, BorderLayout.SOUTH);

            this.setSize(1000, 750);
            this.setLocationRelativeTo(parent);
            this.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this.parent,"Something Went Wrong or there is no medicines to select from ","ERROR!",JOptionPane.ERROR_MESSAGE);
        }
    }

    // Costume Medicine Card
    private class MedicineCard extends JPanel {
        private JCheckBox checkBox;

        public MedicineCard(String name, double price, int stock) {
            this.setLayout(new BorderLayout(15, 0));
            this.setBackground(CARD_BG);
            this.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                    new EmptyBorder(12, 12, 12, 12)
            ));

            // Checkbox on the left
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
            checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Info panel for Name and Details
            JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));
            infoPanel.setOpaque(false);

            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
            nameLabel.setForeground(TEXT_COLOR);

            JLabel detailsLabel = new JLabel("$" + String.format("%.2f", price) + " • " + stock + " in stock");
            detailsLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            detailsLabel.setForeground(new Color(100, 110, 120));

            infoPanel.add(nameLabel);
            infoPanel.add(detailsLabel);

            this.add(checkBox, BorderLayout.WEST);
            this.add(infoPanel, BorderLayout.CENTER);
        }
    }

    // helper function to style buttons
    private JButton styleButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return btn;
    }
}