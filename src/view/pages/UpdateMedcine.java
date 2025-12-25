package view.pages;

import model.Medicine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

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
        Medicine res = mc.updateMedicine(new Medicine(
                id,
                this.nameInput.getText(),
                BigDecimal.valueOf(Float.parseFloat(this.sellPriceInput.getText())),
                BigDecimal.valueOf(Float.parseFloat(this.purchasePriceInput.getText())),
                this.descriptionInput.getText(),
                this.formInput.getText(),
                Integer.parseInt(this.stockInput.getText()),
                Date.valueOf(this.exDataInput.getText()).toLocalDate()
        ));
        if (res != null) {
            JOptionPane.showMessageDialog(parent,"Medicine Updated With Success","Medicine Updated", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent, "Something went Wrong Could not Update this  Medicine", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}