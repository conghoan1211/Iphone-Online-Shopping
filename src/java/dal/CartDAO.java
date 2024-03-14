/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.profile.Account;
import entity.product.ColorProduct;
import entity.Item;
import entity.product.ModelProduct;
import entity.product.Product_Variant;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import service.Cart;

/**
 *
 * @author admin
 */
public class CartDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     *
     *
     * @param a
     * @param cart
     * @param delivered
     * @param discount
     * @param accepted
     * @param feedback
     * @return
     */
    public boolean addOrder(Account a, Cart cart, int delivered, int discount, int accepted, int feedback) {
        LocalDate curDate = LocalDate.now();
        String date_raw = curDate.toString();
        Date date = Date.valueOf(date_raw);

        try {
            String query = "INSERT INTO Orders (username, order_date, delivered, discount, total_price, accepted, feedback)"
                    + "  VALUES(?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, a.getUsername());
            ps.setDate(2, date);
            ps.setInt(3, delivered);
            ps.setInt(4, discount);
            ps.setInt(5, cart.getTotalMoney());
            ps.setInt(6, accepted);
            ps.setInt(7, feedback);

            ps.executeUpdate();
//            return true;
            // take id of order table that have just added
            String query1 = "SELECT TOP 1 order_id FROM Orders ORDER BY order_id DESC";
            PreparedStatement ps1 = connection.prepareStatement(query1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("order_id");
                for (Item i : cart.getItems()) {
                    String query2 = "INSERT INTO OrderDetail (order_id, product_id, quantity, color_id, price, total_price)"
                            + "VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps2 = connection.prepareStatement(query2);
                    ps2.setInt(1, oid);
                    ps2.setInt(2, i.getProduct().getId());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setInt(4, i.getColor().getColor_id());
                    ps2.setInt(5, i.getProduct().getCurrentPrice());
                    ps2.setInt(6, i.getQuantity() * i.getProduct().getCurrentPrice());
                    ps2.executeUpdate();
                }
            }
            // update stock of product 
            String sql3 = "UPDATE product SET quantity_in_stock = quantity_in_stock - ?, "
                    + "amount_of_sold = amount_of_sold + ?\n"
                    + "where id = ?";
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                ps3.setInt(1, i.getQuantity());
                ps3.setInt(2, i.getQuantity());
                ps3.setInt(3, i.getProduct().getId());
                ps3.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ModelProduct> getAllSizeProduct() {
        List<ModelProduct> list = new ArrayList<>();

        String query = "SELECT * FROM Product_Size";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ModelProduct(rs.getInt(1),
                        rs.getString(2)));
            }

        } catch (SQLException e) {
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

    public List<ColorProduct> getAllColorProduct() {
        List<ColorProduct> list = new ArrayList<>();

        String query = "SELECT * FROM Product_Color";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ColorProduct(rs.getInt(1),
                        rs.getString(2)));
            }

        } catch (SQLException e) {
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

    public ColorProduct getColorById(int colorId) {
        String query = "select * from Product_color where colorid = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, colorId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new ColorProduct(rs.getInt(1),
                        rs.getString(2));
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
        return null;
    }

    public List<Product_Variant> getAllProVariant() {
        List<Product_Variant> list = new ArrayList<>();

        String query = "SELECT * FROM Product_Variant";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product_Variant(rs.getInt(1),
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

    public List<Product_Variant> getAllProVariantByid(int pid) {
        List<Product_Variant> list = new ArrayList<>();

        String query = "SELECT * FROM Product_Variant WHERE product_id = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product_Variant(rs.getInt(1),
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

    public boolean addProVarientById(int pid, int color_id, int model_id) {
        String query = "INSERT INTO Product_Variant (product_id, color_id, model_id)"
                + " VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, color_id);
            ps.setInt(3, model_id);
            ps.executeUpdate();

            return true;
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
        return false;
    }

    public boolean addProVarientById(int pid, int color_id) {
        String query = "INSERT INTO Product_Variant (product_id, color_id)"
                + " VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, color_id);
            ps.executeUpdate();

            return true;
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
        return false;
    }

    public boolean addProVarientByMId(int pid, int model_id) {
        String query = "INSERT INTO Product_Variant (product_id, model_id)"
                + " VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, model_id);
            ps.executeUpdate();

            return true;
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
        return false;
    }

    public static void main(String[] args) {
        LocalDate curDate = LocalDate.now();

        // Chuyển đổi LocalDate thành LocalDateTime (mặc định giờ và phút sẽ là 00:00)
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Định dạng ngày và giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Chuyển đổi thành chuỗi và in ra
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Ngày và giờ hiện tại: " + formattedDateTime);

//        CartDAO d = new CartDAO();
//
//        ColorProduct co = d.getColorById(1);
//        System.out.println(co);
//
////        boolean a = d.addProVarientById(2, 2, 2);
////        if (a) {
////            System.out.println("ok");
////        } else {
////            System.out.println("none");
////        }
//        List<Product_Variant> list = d.getAllProVariantByid(42);
//        for (Product_Variant productVarient : list) {
//            System.out.println(productVarient);
//        }
    }
}
