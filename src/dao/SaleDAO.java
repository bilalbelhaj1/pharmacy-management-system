package dao;

import model.Sale;
import model.SaleItem;

import java.sql.*;
import java.util.List;

/**
 * @author $(bilal belhaj)
 **/


public class SaleDAO {
    private Connection conn;
    public SaleDAO() {
        try {
            conn = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not connect to the database");
        }
    }

    // create sale

    public void createSale(Sale sale, List<SaleItem> items) throws SQLException {
        // create a sale row
        PreparedStatement stm1 = conn.prepareStatement("INSERT INTO sale(date, total, status) VALUES (?,?,?)");
        Statement stm2 = conn.createStatement();
        PreparedStatement stm3 = conn.prepareStatement("INSERT INTO sale_item(sale_id, medicine_id, quantity, price) VALUES (?,?,?,?)");
        stm1.setDate(1, Date.valueOf(sale.getDate()));
        stm1.setBigDecimal(2, sale.getTotal());
        stm1.setString(3, sale.getStatus().toString());

        // insert sale
        stm1.executeUpdate();

        // get its id
        ResultSet res = stm2.executeQuery("SELECT id FROM sale ORDER BY id DESC LIMIT 1");
        int id = 0;
        if (res.next()) {
            id = res.getInt("id");
        }

        // to insert its items
        if(id > 0) {
            for (SaleItem item : items) {
                stm3.setInt(1, id);
                stm3.setInt(2, item.getMedicineId());
                stm3.setInt(3, item.getQuantity());
                stm3.setBigDecimal(4, item.getPrice());
                stm3.executeUpdate();
            }
        }

    }

}
