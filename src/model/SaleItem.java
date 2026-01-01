package model;

import java.math.BigDecimal;

/**
 * @author $(bilal belhaj)
 **/
public class SaleItem {
    private BigDecimal total;
    private int quantity;
    private final int medicineId;
    private String name;
    private BigDecimal price;
    public SaleItem(int medicineId, int quantity, BigDecimal total, String name, BigDecimal price) {
        this.medicineId = medicineId;
        this.total = total;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public SaleItem(int medicineId, int quantity, BigDecimal total) {
        this.medicineId = medicineId;
        this.total = total;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getMedicineId() {
        return this.medicineId;
    }

    public String getName() { return this.name; }

    public BigDecimal getPrice() { return this.price; }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
