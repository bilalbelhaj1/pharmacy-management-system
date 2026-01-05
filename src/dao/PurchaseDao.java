package dao;
import model.Purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/
public class PurchaseDao {
    Connection conn;
    public PurchaseDao() {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create new Purchase

    public List<Purchase> getAll() {
        List<Purchase> purchases = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.date, p.supplier_id, p.total, CONCAT(s.first_name, \" \" ,s.last_name) as name, s.email,s.phone_number  FROM purchase as p JOIN supplier as s ON p.supplier_id = s.id;";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                purchases.add(new Purchase(
                        res.getInt("id"),
                        res.getInt("supplier_id"),
                        res.getDate("date").toLocalDate(),
                        res.getBigDecimal("total"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }
}
