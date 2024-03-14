/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.IConstant;
import entity.Orders;
import entity.profile.Account;
import entity.product.Category;
import entity.product.ColorProduct;
import entity.product.Product;
import entity.product.ProductDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ManagerDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     *
     * @param pid
     * @return
     */
    public boolean deleteProduct(String pid) {
        String query = "Delete from Product where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pid);
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

    /**
     *
     * @param title
     * @param image
     * @param describe
     * @param currentprice
     * @param oldPrice
     * @param origin
     * @param status
     * @param stock
     * @param category
     * @param sold
     * @param p
     * @return
     */
    public boolean insertProduct(int category, String image, String title, int oldPrice, int currentprice,
            int sold, String origin, String status, int stock, String describe, Product p) {

        String query = "INSERT INTO Product (Category_id, [image], title, old_price, current_price, amount_of_sold, origin, [status], quantity_in_stock, describe) "
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, category);
            ps.setString(2, image);
            ps.setString(3, title);
            ps.setInt(4, oldPrice);
            ps.setInt(5, currentprice);
            ps.setInt(6, sold);
            ps.setString(7, origin);
            ps.setString(8, status);
            ps.setInt(9, stock);
            ps.setString(10, describe);
            ps.executeUpdate();

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
        String query1 = "INSERT INTO Product_Variant (product_id, color_id) "
                + " VALUES (?, ?)";

        List<ColorProduct> listCol = p.getListColor();
        try {
            PreparedStatement ps1 = connection.prepareStatement(query1);
            for (ColorProduct c : listCol) {
                ps1.setInt(1, p.getId());
                ps1.setInt(2, c.getColor_id());
                ps1.executeUpdate();
            }
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

    public boolean updateProduct(int productId, int category, String image, String title, int oldPrice, int currentPrice,
            String origin, String status, int stock, String describe, Product oldP) {
        String query = "UPDATE Product SET "
                + "category_id = ?, "
                + "image = ?, "
                + "title = ?, "
                + "old_price = ?, "
                + "current_price = ?, "
                + "origin = ?, "
                + "[status] = ?, "
                + "quantity_in_stock = ?, "
                + "describe = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, category);
            ps.setString(2, image);
            ps.setString(3, title);
            ps.setInt(4, oldPrice);
            ps.setInt(5, currentPrice);
            ps.setString(6, origin);
            ps.setString(7, status);
            ps.setInt(8, stock);
            ps.setString(9, describe);
            ps.setInt(10, productId);

            ps.executeUpdate();
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

        String query2 = "DELETE FROM Product_Variant WHERE product_id = ? ";
        String query1 = "INSERT INTO Product_Variant (product_id, color_id) "
                + " VALUES (?, ?)";

        List<ColorProduct> listCol = oldP.getListColor();
        try {
            PreparedStatement deletePS = connection.prepareStatement(query2);
            deletePS.setInt(1, oldP.getId());
            deletePS.executeUpdate();

            PreparedStatement ps1 = connection.prepareStatement(query1);
            for (ColorProduct c : listCol) {
                ps1.setInt(1, oldP.getId());
                ps1.setInt(2, c.getColor_id());
                ps1.executeUpdate();
            }
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

    public Account getAccountByUser(int accid) {
        String query = "Select * from ACCOUNT where accID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
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

    public boolean updateAccount(int accID, String username, String password, int isAdmin, int isBlock) {
        String query2 = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + "      ,[isAdmin] = ?\n"
                + "      ,[isBlock] = ?\n"
                + " WHERE [username] = ?";
        try {
            ps = connection.prepareStatement(query2);
            ps.setString(1, password);
            ps.setInt(2, isAdmin);
            ps.setInt(3, isBlock);
            ps.setString(4, username);

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

    public boolean insertCategory(int cateID, String cateName) {
        String query = "INSERT INTO Category (id, name) VALUES (?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cateID);
            ps.setString(2, cateName);

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

    public boolean updateCategory(String cateName, int cateID) {
        String query = "Update Category SET "
                + "[name] = ? "
                + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cateName);
            ps.setInt(2, cateID);

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

    public Category getCategoryByID(int cid) {
        DAO dao = new DAO();
        List<Category> listC = dao.getAllCategory();
        for (Category cate : listC) {
            if (cate.getCid() == cid) {
                return cate;
            }
        }
        return null;
    }

    public int getAmountOfCategory() {
        DAO dao = new DAO();
        List<Category> listC = dao.getAllCategory();
        int cnt = listC.size();
        return cnt;
    }

    public boolean deleteCategory(String cid) {
        String query = "Delete from Category where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cid);
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

    public boolean addProductDetail(int pid, String detail_img1, String detail_img2, String detail_im3) {
        String query = "INSERT INTO [dbo].[Product_Detail]\n"
                + "           ([pid] ,[detail_img1], [detail_img2], [detail_img3])\n"
                + "     VALUES (?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setString(2, detail_img1);
            ps.setString(3, detail_img2);
            ps.setString(4, detail_im3);

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

    public boolean updateProductDetail(int pid, String detail_img1, String detail_img2, String detail_img3) {

        ProductDetail pd = this.getProductDetailById(pid);
        if (pd == null) {
            boolean add = this.addProductDetail(pid, detail_img1, detail_img2, detail_img3);
            return add;
        } else {
            String query = "UPDATE Product_Detail SET "
                    + "detail_img1 = ?, "
                    + "detail_img2 = ?, "
                    + "detail_img3 = ? "
                    + "WHERE pid = ?;";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, detail_img1);
                ps.setString(2, detail_img2);
                ps.setString(3, detail_img3);
                ps.setInt(4, pid);

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

    }

    public ProductDetail getProductDetailById(int pid) {
        String query = "select * from Product_Detail where pid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new ProductDetail(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
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

    public List getAllOrders(String type, String text, int select) {
        List<Orders> list = new ArrayList<>();
        String query = "Select * from Orders where 1 = 1 and accepted IS NOT NULL ";

        if (type != null) {
            switch (type) {
                case "1":  // order not accepted yet
                    query += "and accepted = " + IConstant.DEFAUT;
                    break;
                case "2":  // order not delivered yet or delivering
                    query += "and accepted = " + IConstant.IS_ACCEPTED + " and delivered = " + IConstant.DEFAUT;
                    break;
                case "3":    // order delivered 
                    query += "and accepted = " + IConstant.IS_ACCEPTED + " and delivered = " + IConstant.IS_DELIVERED;
                    break;
                case "4":    // order purchased 
                    query += "and accepted = " + IConstant.IS_ACCEPTED + " and delivered = " + IConstant.IS_PURCHASED;
                    break;
                case "5":    // order canceled 
                    query += "and accepted = " + IConstant.IS_CANCELED + " or accepted = " + IConstant.IS_NO_ACCEPTED_BY_ADMIN;
                    break;
                case "6":    // order return or refund 
                    query += "and delivered = " + IConstant.IS_RETURN_OR_REFUND;
                    break;
                case "7":    // order delivered no success 
                    query += "and delivered = " + IConstant.IS_DELIVERED_NO_SUCCESS;
                    break;
                default:
                    break;
            }
        }
        if (text != null && select != -1) {
            switch (select) {
                case 1:
                    query += " and username like ?";
                    break;
                case 2:
                    query += " ";
                    break;
                case 3:
                    query += " and order_id = ?" ;
                    break;
                default:
                    break;
            }
        }

        query += " order by order_id DESC";
        try {
            ps = connection.prepareStatement(query);
            if (text != null && select != -1) {
                ps.setString(1, "%" + text +"%");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Orders(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)));
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

    public static void main(String[] args) {
        ManagerDAO mdao = new ManagerDAO();
//        boolean acc = mdao.updateProductDetail(40, "https://down-vn.img.susercontent.com/file/a3e6b06718e9445012997852c5c44145_tn",
//                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lmywwev54e730e_tn",
//                "https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lmywwev5jufzda_tn");
//        if (acc) {
//            System.out.println("ok");
//        } else {
//            System.out.println("none");
//        }
//        ProductDetail pd = mdao.getProductDetailById("1");
//        System.out.println(pd);
//        Category c = mdao.getCategoryByID(11);
//        int c = mdao.getAmountOfCategory();
//        System.out.println(c);

        List<Orders> list = mdao.getAllOrders("0", "j", 1);
        for (Orders orders : list) {
            System.out.println(orders);
        }

    }

}
