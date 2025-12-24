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
public class AddMedicine extends JFrame implements ActionListener{
    JTextField nameInput;
    JTextField purchasePriceInput;
    JTextField sellPriceInput;
    JTextField formInput;
    JTextArea descriptionInput;
    JTextField exDataInput;
    JTextField stockInput;
    JDialog successDialog;
    JDialog errorDialog;
    JFrame parent;
    Button addButton;
    MedicineController mc = new MedicineController();
    public AddMedicine(JFrame parent) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.parent = parent;
        this.setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new GridLayout(8, 1));

        // name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("Medicine Title: ");
        nameInput = new JTextField();
        nameInput.setColumns(30);
        namePanel.add(nameLabel);
        namePanel.add(nameInput);

        // purchase price
        JPanel purchasePricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel purchasePriceLabel = new JLabel("Purchase Price: ");
        purchasePriceInput = new JTextField();
        purchasePriceInput.setColumns(30);
        purchasePricePanel.add(purchasePriceLabel);
        purchasePricePanel.add(purchasePriceInput);

        // sell price
        JPanel sellPricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel sellPriceLabel = new JLabel("Sell Price: ");
        sellPriceInput = new JTextField();
        sellPriceInput.setColumns(30);
        sellPricePanel.add(sellPriceLabel);
        sellPricePanel.add(sellPriceInput);
        // form
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel formLabel = new JLabel("Form: ");
        formInput = new JTextField();
        formInput.setColumns(30);
        formPanel.add(formLabel);
        formPanel.add(formInput);

        // description
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionInput = new JTextArea();
        descriptionInput.setColumns(30);
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionInput);
        // stock
        JPanel stockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel stockLabel = new JLabel("Stock: ");
        stockInput = new JTextField();
        stockInput.setColumns(30);
        stockPanel.add(stockLabel);
        stockPanel.add(stockInput);
        // expiration date
        JPanel exDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel exDateLabel = new JLabel("Expiration Date(0000-00-00): ");
        exDataInput = new JTextField();
        exDataInput.setColumns(30);
        exDatePanel.add(exDateLabel);
        exDatePanel.add(exDataInput);

        // buttons
        Button cancelButton = new Button("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(e-> this.dispose());

        addButton = new Button("Add");
        addButton.setBackground(Color.GREEN);
        addButton.addActionListener(this);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(addButton);

        mainPanel.add(namePanel);
        mainPanel.add(purchasePricePanel);
        mainPanel.add(sellPricePanel);
        mainPanel.add(formPanel);
        mainPanel.add(descriptionPanel);
        mainPanel.add(stockPanel);
        mainPanel.add(exDatePanel);
        mainPanel.add(buttonsPanel);

        // success dialog
        successDialog = new JDialog();
        successDialog.setTitle("Success..");
        successDialog.setSize(300, 300);
        JLabel successLabel = new JLabel("Medicine added");
        successLabel.setAlignmentX(JLabel.CENTER);
        successLabel.setForeground(Color.GREEN);
        successLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        successDialog.add(successLabel);
        // error
        errorDialog = new JDialog();
        errorDialog.setTitle("Error");
        errorDialog.setSize(300, 300);
        JLabel errorLabel = new JLabel("Could not add medicine Something went wrong");
        errorLabel.setBackground(Color.RED);
        errorDialog.add(errorLabel);

        this.add(mainPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton) {
            try {
                mc.addMedicine(nameInput.getText(), descriptionInput.getText(), BigDecimal.valueOf(Integer.parseInt(sellPriceInput.getText())), BigDecimal.valueOf(Integer.parseInt(purchasePriceInput.getText())), formInput.getText(), Integer.parseInt(stockInput.getText()) , Date.valueOf(exDataInput.getText()));
                this.dispose();
                this.successDialog.setLocationRelativeTo(parent);
                this.successDialog.setVisible(true);
            } catch (SQLException ex) {
                this.dispose();
                this.errorDialog.setLocationRelativeTo(parent);
                this.errorDialog.setVisible(true);
            }
        }
    }
}
