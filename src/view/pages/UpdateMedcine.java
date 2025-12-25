package view.pages;

import model.Medicine;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;

public class UpdateMedcine extends AddMedicine {
    private int id;
    Medicine medicine;
    public UpdateMedcine(JPanel parent, int id) {
        super(null);
        this.id = id;
        try {
            medicine = this.mc.getById(this.id);
            System.out.println(medicine.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setTitle("Update Medicine");
        loadData();
    }

    private void loadData() {
        this.nameInput.setText(medicine.name());
        this.purchasePriceInput.setText(medicine.purchasePrice() + "");
        this.sellPriceInput.setText(medicine.sellPrice() + "");
        this.formInput.setText(medicine.form());
        this.descriptionInput.setText(medicine.description());
        this.stockInput.setText(medicine.stock() + "");
        this.exDataInput.setText(medicine.expiration_date().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Update logic goes here");
        System.out.println(this.id);
    }
}