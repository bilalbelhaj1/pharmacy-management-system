package model;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author $(bilal belhaj)
 **/
public class Purchase {
    private int id;
    private final int supplierId;
    private final LocalDate date;
    private final BigDecimal total;

    public Purchase(int id, int supplierId, LocalDate date, BigDecimal total) {
        this.id = id;
        this.supplierId = supplierId;
        this.date = date;
        this.total = total;
    }

    public Purchase(int supplierId, LocalDate date, BigDecimal total) {
        this.supplierId = supplierId;
        this.date = date;
        this.total = total;
    }

    public int getId() {
        return this.id;
    }
    public int getSupplierId() {
        return this.supplierId;
    }
    public LocalDate getDate() {
        return this.date;
    }

    public BigDecimal getTotal() {
        return this.total;
    }
}
