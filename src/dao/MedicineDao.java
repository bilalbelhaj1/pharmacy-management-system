package dao;

import model.Medicine;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
               BigDecimal.valueOf(res.getDouble("price")),
               res.getString("description"),
               res.getString("form"),
               res.getInt("stock"),
               LocalDate.of(ex.getYear(), ex.getMonth(), ex.getDay())
            ));
        }
        return medicines;
    }

    public void addMedicine(String name, BigDecimal price, String description, String form, int stock, LocalDate expirationDat) throws SQLException {
        String sql = "INSERT INTO medicine(name,price,description,form,stock,expiration_date) VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, name);
        stm.setBigDecimal(2, price);
        stm.setString(3, description);
        stm.setString(4, form);
        stm.setInt(5, stock);
        stm.setDate(6, java.sql.Date.valueOf(expirationDat));

        int i = stm.executeUpdate();
    }
}
