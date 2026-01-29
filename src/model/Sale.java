package model;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/

public class Sale {
    private int id;
    private final LocalDate date;
    private final BigDecimal total;
    private final SaleStatus status;
    private List<SaleItem> items;
    private int totalMed;

    public Sale(int id, LocalDate date, BigDecimal total, SaleStatus status, int totalMed) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.status = status;
        this.totalMed = totalMed;
    }
    public Sale(LocalDate date, BigDecimal total, SaleStatus status) {
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public SaleStatus getStatus() {
        return this.status;
    }

    public int getTotalMed() {
        return this.totalMed;
    }

    public void setItems(List<SaleItem> items){
        this.items = items;
    }

    public List<SaleItem> getItems() {
        return this.items;
    }

    public int getTotalMedicines() {
        if(items.isEmpty()) return 0;
        int s = 0;
        for (SaleItem item : items) {
            s += item.getQuantity();
        }
        return s;
    }
}
