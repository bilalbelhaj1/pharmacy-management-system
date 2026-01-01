package dao;

import model.Sale;
import model.SaleItem;
import model.SaleStatus;

import java.sql.*;
import java.util.ArrayList;
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
                itemStmt.setBigDecimal(4, item.getTotal());
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

    public List<Sale> getSales() throws SQLException{
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sale";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            sales.add(
                new Sale(
                    res.getInt("id"),
                    res.getDate("date").toLocalDate(),
                    res.getBigDecimal("total"),
                    SaleStatus.PAID
                )
            );
        }
        return sales;
    }

    public void deleteSale(int saleId) throws SQLException {
        String deleteSql = "DELETE FROM sale WHERE id = ?";
        PreparedStatement deleteStm = conn.prepareStatement(deleteSql);
        deleteStm.setInt(1, saleId);
        deleteStm.executeUpdate();
    }

    public List<SaleItem> getSaleItems(int saleId) throws SQLException {
        List<SaleItem> items = new ArrayList<>();
        String sql = "SELECT m.id, m.name ,s.medicine_id, s.quantity, s.price as total, m.sell_price as price FROM sale_item as s JOIN medicine as m ON s.medicine_id = m.id WHERE sale_id = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, saleId);
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            items.add(new SaleItem(
                    res.getInt("medicine_id"),
                    res.getInt("quantity"),
                    res.getBigDecimal("price"),
                    res.getString("name"),
                    res.getBigDecimal("price")
            ));
        }
        return items;
    }

}
