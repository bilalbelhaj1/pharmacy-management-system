package controller;

import dao.MedicineDao;
import model.Medicine;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class MedicineController {
    MedicineDao dao;
    public MedicineController() {
        dao = new MedicineDao();
    }

    public List<Medicine> getAllMedicines(){
        try {
            return dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Medicine getById(int id) throws SQLException {
        return dao.getMedicineById(id);
    }

    public void addMedicine(String name, String description, BigDecimal sellPrice, BigDecimal purchasePrice, String form, int stock, Date expirationDate) throws SQLException {
        dao.addMedicine(name, description, sellPrice, purchasePrice, form,stock, expirationDate);
    }

    public void deleteMedicine(int id) throws SQLException {
        dao.deleteMedicine(id);
    }
    public Medicine updateMedicine(Medicine medicine) {
        try {
            dao.updateMedicine(medicine);
            return medicine;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
