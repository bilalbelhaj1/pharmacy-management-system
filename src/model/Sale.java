package model;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.time.LocalDate;

/**
 * @author $(bilal belhaj)
 **/

public class Sale {
    private int id;
    private final LocalDate date;
    private final BigDecimal total;
    private final SaleStatus status;

    public Sale(int id, LocalDate date, BigDecimal total, SaleStatus status) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.status = status;
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

}
