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
        String saleSql = "INSERT INTO sale(date, total, status) VALUES (?,?,?)";
        String itemSql = "INSERT INTO sale_item(sale_id, medicine_id, quantity, price) VALUES (?,?,?,?)";
        String decreaseStockSql = "UPDATE medicine SET stock = stock - ? WHERE id = ?";
        conn.setAutoCommit(false);

        try (
                PreparedStatement saleStmt = conn.prepareStatement(
                        saleSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement itemStmt = conn.prepareStatement(itemSql);
                PreparedStatement decreaseStockStmt = conn.prepareStatement(decreaseStockSql);
        ) {
            // Insert sale
            saleStmt.setDate(1, Date.valueOf(sale.getDate()));
            saleStmt.setBigDecimal(2, sale.getTotal());
            saleStmt.setString(3, sale.getStatus().toString());
            saleStmt.executeUpdate();

            // Get sale ID
            ResultSet rs = saleStmt.getGeneratedKeys();
            int saleId;
            if (!rs.next()) {
                throw new SQLException("Failed to retrieve sale ID");
            }
            saleId = rs.getInt(1);

            // Insert items
            for (SaleItem item : items) {
                itemStmt.setInt(1, saleId);
                itemStmt.setInt(2, item.getMedicineId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setBigDecimal(4, item.getPrice());
                itemStmt.executeUpdate();
                decreaseStockStmt.setInt(1, item.getQuantity());
                decreaseStockStmt.setInt(2, item.getMedicineId());
                decreaseStockStmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }


}
