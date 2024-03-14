/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.chart.RevenueData;
import entity.chart.SellData;
import entity.chart.StockData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChartDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<SellData> getAllSellData() {
        List<SellData> list = new ArrayList<>();
        String query = "SELECT YEAR(order_date) AS order_year, \n"
                + "    MONTH(order_date) AS order_month, \n"
                + "    COUNT(*) AS order_count\n"
                + "FROM Orders\n"
                + "GROUP BY YEAR(order_date), MONTH(order_date)\n"
                + "ORDER BY order_year, order_month;";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SellData(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public List<RevenueData> getAllRevenueData() {

        List<RevenueData> list = new ArrayList<>();
        String query = "SELECT MONTH(order_date) AS order_month, \n"
                + "    YEAR(o.order_date) AS order_year,\n"
                + "    p.category_id,\n"
                + "    SUM(od.total_price) AS total_price_per_category\n"
                + "FROM Orders o\n"
                + "INNER JOIN OrderDetail od ON o.order_id = od.order_id\n"
                + "INNER JOIN Product p ON od.product_id = p.id\n"
                + "GROUP BY YEAR(o.order_date), MONTH(order_date) , p.category_id";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new RevenueData(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public List<StockData> getAllStockData() {
        List<StockData> list = new ArrayList<>();
        String query = "SELECT c.[name], SUM(quantity_in_stock) as total_stock\n"
                + "FROM Product p join category c on p.category_id = c. id\n"
                + "GROUP BY c.[name];";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new StockData(rs.getString(1),
                        rs.getInt(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public int getTotalRevenue() {
        String query = "SELECT SUM(total_price) as total_revenue\n"
                + "FROM Orders;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    public int getAllOrder() {
        String query = "SELECT count(order_id) as total_revenue\n"
                + "FROM Orders;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    public int getAllQuantityInStock() {
        String query = "SELECT SUM(quantity_in_stock) as total_stock\n"
                + "FROM Product;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    public int getTotalAmountOfSold() {
        String query = "SELECT SUM(amount_of_sold) as total_sold\n"
                + "FROM Product;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ChartDAO cdao = new ChartDAO();
        List<StockData> l = cdao.getAllStockData();
        for (StockData sellData : l) {
            System.out.println(sellData);
        }

    }

}
