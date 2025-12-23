package controller;

import dao.MedicineDao;
import model.Medicine;

import java.sql.SQLException;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class MedicineController {
    MedicineDao dao;
    public MedicineController() {
        dao = new MedicineDao();
    }

    public List<Medicine> getAllMedicines() throws SQLException {
        return dao.getAll();
    }
}
