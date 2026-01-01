package controller;

import dao.SaleDAO;
import model.Sale;
import model.SaleItem;
import model.SaleStatus;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class SaleController {
    private SaleDAO dao;

    public SaleController() {
        this.dao = new SaleDAO();
    }

    public boolean createSale(List<SaleItem> items) {
        try {
            BigDecimal total = items.stream()
                    .map(item -> item.getTotal())
                    .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
            dao.createSale(new Sale(LocalDate.now(), total, SaleStatus.PAID), items);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Sale> getSales() {
        List<Sale> sales = new ArrayList<>();
        try {
            sales = dao.getSales();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public boolean deleteSale(int id) {
        try {
            dao.deleteSale(id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<SaleItem> getSaleItems(int id) {
        try {
            return dao.getSaleItems(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not get items");
        }
    }
}
