package dao;

import model.Medicine;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class MedicineDao {
    private Connection conn;

    public MedicineDao() {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Could not connect to database ");
        }
    }

    public List<Medicine> getAll() throws SQLException {
        String query = "SELECT * FROM medicine";
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery(query);

        List<Medicine> medicines = new ArrayList<>();

        while (res.next()) {
            Date ex = res.getDate("expiration_date");
            medicines.add(new Medicine(
               res.getInt("id"),
               res.getString("name"),
               BigDecimal.valueOf(res.getDouble("sell_price")),
               BigDecimal.valueOf(res.getDouble("purchase_price")),
               res.getString("description"),
               res.getString("form"),
               res.getInt("stock"),
               LocalDate.of(2027, 12,12)
            ));
        }
        return medicines;
    }

    public void addMedicine(String name, String description, BigDecimal sellPrice, BigDecimal purchasePrice, String form, int stock, Date expirationDate) throws SQLException {
        String sql = "INSERT INTO medicine(name,description,sell_price,purchase_price,form,stock,expiration_date) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, name);
        stm.setString(2, description);
        stm.setBigDecimal(3, sellPrice);
        stm.setBigDecimal(4, purchasePrice);
        stm.setString(5, form);
        stm.setInt(6, stock);
        stm.setDate(7, expirationDate);

        int i = stm.executeUpdate();
    }

    public Medicine getMedicineById(int id) throws SQLException{
        String sql = "SELECT * FROM medicine WHERE id = ? LIMIT 1";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet res = stm.executeQuery();
        if (res.next()) {
            return new Medicine(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getBigDecimal("sell_price"),
                    res.getBigDecimal("purchase_price"),
                    res.getString("description"),
                    res.getString("form"),
                    res.getInt("stock"),
                    res.getDate("expiration_date").toLocalDate()
            );
        }
        return null;
    }

    public void deleteMedicine(int id) throws SQLException {
        String sql = "DELETE FROM medicine WHERE id = ?";
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(1, id);
        int i = stm.executeUpdate();
    }

    public void updateMedicine(Medicine newMedicine) {
        // delete the current medicine and insert the new one
    }

    public List<Medicine> getLowStockMedicines() throws SQLException {
        String sql = "SELECT * FROM medicine WHERE stock < 10";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet res = stm.executeQuery();
        List<Medicine> lowStockMedicines = new ArrayList<>();
        while (res.next()) {
            lowStockMedicines.add(new Medicine(
               res.getInt("id"),
               res.getString("name"),
               res.getBigDecimal("sell_price"),
               res.getBigDecimal("purchase_price"),
               res.getString("description"),
               res.getString("form"),
               res.getInt("stock"),
               res.getDate("expiration_date").toLocalDate()
            ));
        }
        return lowStockMedicines;
    }
}
