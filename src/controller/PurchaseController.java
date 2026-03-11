package controller;

import dao.PurchaseDao;
import model.Purchase;

import java.sql.SQLException;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class PurchaseController {
    PurchaseDao dao;
    public PurchaseController() {
        dao = new PurchaseDao();
    }

    public List<Purchase> getAll() {
        return dao.getAll();
    }
    public int deletePurchase(int id) {
        try {
            dao.deletePurchase(id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
