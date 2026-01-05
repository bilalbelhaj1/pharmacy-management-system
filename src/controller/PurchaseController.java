package controller;

import dao.PurchaseDao;
import model.Purchase;

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
}
