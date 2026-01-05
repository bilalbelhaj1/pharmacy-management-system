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
            String sql = "SELECT * FROM purchase";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                purchases.add(new Purchase(
                        res.getInt("id"),
                        res.getInt("supplier_id"),
                        res.getDate("date").toLocalDate(),
                        res.getBigDecimal("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }
}
