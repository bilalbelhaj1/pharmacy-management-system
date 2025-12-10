package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author $(bilal belhaj)
 **/
public class Medicine {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private String form;
    private int stock;
    private LocalDate expiration_date;

    public Medicine(int id, String name, BigDecimal price, String description, String form, int stock, LocalDate expiration_date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.form = form;
        this.stock = stock;
        this.expiration_date = expiration_date;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
