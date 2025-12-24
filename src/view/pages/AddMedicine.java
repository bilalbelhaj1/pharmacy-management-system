package view.pages;

import controller.MedicineController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @author $(bilal belhaj)
 **/
public class AddMedicine extends JFrame implements ActionListener {
    protected JTextField nameInput, purchasePriceInput, sellPriceInput, formInput, exDataInput, stockInput;
    protected JTextArea descriptionInput;
    protected JDialog successDialog, errorDialog;
    protected JFrame parent;
    protected JButton addButton;
    protected MedicineController mc = new MedicineController();

    public AddMedicine(JFrame parent) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(550, 600);
        this.parent = parent;
        this.setLocationRelativeTo(parent);
        this.setTitle("Add Medicine");
        this.getContentPane().setBackground(new Color(248, 249, 250));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(248, 249, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add all fields
        mainPanel.add(createInputPanel("Medicine Title: ", nameInput = new JTextField(30)));
        mainPanel.add(createInputPanel("Purchase Price: ", purchasePriceInput = new JTextField(30)));
        mainPanel.add(createInputPanel("Sell Price: ", sellPriceInput = new JTextField(30)));
        mainPanel.add(createInputPanel("Form: ", formInput = new JTextField(30)));

        // Description panel with scroll
        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BorderLayout(5,5));
        descPanel.setBackground(new Color(248, 249, 250));
        JLabel descLabel = new JLabel("Description: ");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionInput = new JTextArea(5,30);
        descriptionInput.setLineWrap(true);
        descriptionInput.setWrapStyleWord(true);
        descriptionInput.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane descScroll = new JScrollPane(descriptionInput);
        descPanel.add(descLabel, BorderLayout.NORTH);
        descPanel.add(descScroll, BorderLayout.CENTER);
        descPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        mainPanel.add(descPanel);
        mainPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(createInputPanel("Stock: ", stockInput = new JTextField(30)));
        mainPanel.add(createInputPanel("Expiration Date (YYYY-MM-DD): ", exDataInput = new JTextField(30)));

        // Buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2,10,0));
        buttonsPanel.setBackground(new Color(248, 249, 250));
        JButton cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(220,53,69));
        cancelButton.addActionListener(e-> this.dispose());

        addButton = new JButton("Add");
        styleButton(addButton, new Color(40,167,69));
        addButton.addActionListener(this);

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(addButton);
        buttonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(buttonsPanel);

        // Success dialog
        successDialog = new JDialog(this, "Success", true);
        successDialog.setSize(300, 150);
        successDialog.setLayout(new FlowLayout());
        JLabel successLabel = new JLabel("Medicine added successfully");
        successLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        successLabel.setForeground(new Color(40,167,69));
        successDialog.add(successLabel);

        // Error dialog
        errorDialog = new JDialog(this, "Error", true);
        errorDialog.setSize(350, 150);
        errorDialog.setLayout(new FlowLayout());
        JLabel errorLabel = new JLabel("Could not add medicine. Something went wrong.");
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        errorLabel.setForeground(new Color(220,53,69));
        errorDialog.add(errorLabel);

        this.add(mainPanel);
        this.setVisible(true);
    }

    private JPanel createInputPanel(String labelText, JTextField inputField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5,5));
        panel.setBackground(new Color(248, 249, 250));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label, BorderLayout.NORTH);
        panel.add(inputField, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        panel.add(Box.createVerticalStrut(5), BorderLayout.SOUTH);
        return panel;
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton) {
            try {
                mc.addMedicine(
                        nameInput.getText(),
                        descriptionInput.getText(),
                        BigDecimal.valueOf(Integer.parseInt(sellPriceInput.getText())),
                        BigDecimal.valueOf(Integer.parseInt(purchasePriceInput.getText())),
                        formInput.getText(),
                        Integer.parseInt(stockInput.getText()),
                        Date.valueOf(exDataInput.getText())
                );
                this.dispose();
                successDialog.setLocationRelativeTo(parent);
                successDialog.setVisible(true);
            } catch (SQLException ex) {
                this.dispose();
                errorDialog.setLocationRelativeTo(parent);
                errorDialog.setVisible(true);
            }
        }
    }
}
