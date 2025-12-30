package model;

import java.math.BigDecimal;

/**
 * @author $(bilal belhaj)
 **/
public class SaleItem {
    private final BigDecimal price;
    private final int quantity;
    private final int medicineId;
    public SaleItem(int medicineId, int quantity, BigDecimal price) {
        this.medicineId = medicineId;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getMedicineId() {
        return this.medicineId;
    }
}
