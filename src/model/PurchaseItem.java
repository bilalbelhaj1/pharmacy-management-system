package model;

import java.math.BigDecimal;

/**
 * @author $(bilal belhaj)
 **/
public class PurchaseItem {
    private int id;
    private int medicineId;
    private int purchaseId;
    private final int quantity;
    private final BigDecimal price;

    public PurchaseItem(int id, int medicineId, int purchaseId, int quantity, BigDecimal price) {
        this.id = id;
        this.medicineId = medicineId;
        this.purchaseId = purchaseId;
        this.quantity = quantity;
        this.price = price;
    }
    public PurchaseItem(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return this.quantity;
    }

}
