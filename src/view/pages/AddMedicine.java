package view.pages;

import javax.swing.*;
import java.awt.*;

/**
 * @author $(bilal belhaj)
 **/
public class AddMedicine extends JFrame {
    public AddMedicine(JFrame parent) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new GridLayout(8, 1));

        // name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel nameLabel = new JLabel("Medicine Title: ");
        JTextField nameInput = new JTextField();
        nameInput.setColumns(30);
        namePanel.add(nameLabel);
        namePanel.add(nameInput);

        // purchase price
        JPanel purchasePricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel purchasePriceLabel = new JLabel("Purchase Price: ");
        JTextField purchasePriceInput = new JTextField();
        purchasePriceInput.setColumns(30);
        purchasePricePanel.add(purchasePriceLabel);
        purchasePricePanel.add(purchasePriceInput);

        // sell price
        JPanel sellPricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel sellPriceLabel = new JLabel("Sell Price: ");
        JTextField sellPriceInput = new JTextField();
        sellPriceInput.setColumns(30);
        sellPricePanel.add(sellPriceLabel);
        sellPricePanel.add(sellPriceInput);
        // form
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel formLabel = new JLabel("Form: ");
        JTextField formInput = new JTextField();
        formInput.setColumns(30);
        formPanel.add(formLabel);
        formPanel.add(formInput);

        // description
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel descriptionLabel = new JLabel("Description: ");
        JTextArea descriptionInput = new JTextArea();
        descriptionInput.setColumns(30);
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionInput);
        // stock
        JPanel stockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel stockLabel = new JLabel("Stock: ");
        JTextField stockInput = new JTextField();
        stockInput.setColumns(30);
        stockPanel.add(stockLabel);
        stockPanel.add(stockInput);
        // expiration date

        JPanel exDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel exDateLabel = new JLabel("Stock: ");
        JTextField exDataInput = new JTextField();
        exDataInput.setColumns(30);
        exDatePanel.add(exDateLabel);
        exDatePanel.add(exDataInput);

        // buttons
        Button cancelButton = new Button("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(e-> this.dispose());

        Button addButton = new Button("Add");
        addButton.setBackground(Color.GREEN);
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

        this.add(mainPanel);
        this.setVisible(true);
    }
}
