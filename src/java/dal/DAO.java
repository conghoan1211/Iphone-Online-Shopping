/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.IConstant;
import entity.profile.Account;
import entity.product.Category;
import entity.product.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * This method used get all products from database
     *
     * @return list of all products
     */
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.id, p.image, p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status, "
                + "c.id AS cid, c.name AS cname, p.describe, p.quantity_in_stock "
                + "FROM product AS p "
                + "JOIN category c ON p.category_id = c.id";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
//                list.add(new Product(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getInt(4),
//                        rs.getInt(5),
//                        rs.getInt(6),
//                        rs.getString(7),
//                        rs.getString(8),
//                        rs.getInt(9),
//                        rs.getString(10),
//                        rs.getInt(11)));
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOldPrice(rs.getInt("old_price"));
                p.setCurrentPrice(rs.getInt("current_price"));
                p.setAmountOfSold(rs.getInt("amount_of_sold"));
                p.setOrigin(rs.getString("origin"));
                p.setStatus(rs.getString("status"));

                Category c = new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                p.setCate(c);

                p.setDescribe(rs.getString("describe"));
                p.setQuantity_in_stock(rs.getInt("quantity_in_stock"));

                list.add(p);
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
        // Trộn danh sách sản phẩm ngẫu nhiên sau khi lấy dữ liệu từ cơ sở dữ liệu.
        Collections.shuffle(list, new Random());
        return list;
    }

    /**
     * This method used to get all categories from database
     *
     * @return list of all categories
     */
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "Select * from Category Order by id desc";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
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

    public String getCateNameByPid(int pid) {
        Product p = this.getProductByID(pid);
        String cateName = p.getCate().getCname();
        return cateName;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "Select * from Account";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)));
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

    /**
     * This method used to get all products by category ID from database
     *
     * @param cid is category need to get
     * @return list of products by category id
     */
    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + " join category c on p.category_id = c.id where c.id = ?";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOldPrice(rs.getInt("old_price"));
                p.setCurrentPrice(rs.getInt("current_price"));
                p.setAmountOfSold(rs.getInt("amount_of_sold"));
                p.setOrigin(rs.getString("origin"));
                p.setStatus(rs.getString("status"));

                Category c = new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                p.setCate(c);

                p.setDescribe(rs.getString("describe"));
                p.setQuantity_in_stock(rs.getInt("quantity_in_stock"));

                list.add(p);
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

    /**
     *
     * @param pid
     * @return
     */
    public Product getProductByID(String pid) {
        String query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + "join category c on p.category_id = c.id where p.id = ?";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOldPrice(rs.getInt("old_price"));
                p.setCurrentPrice(rs.getInt("current_price"));
                p.setAmountOfSold(rs.getInt("amount_of_sold"));
                p.setOrigin(rs.getString("origin"));
                p.setStatus(rs.getString("status"));

                Category c = new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                p.setCate(c);

                p.setDescribe(rs.getString("describe"));
                p.setQuantity_in_stock(rs.getInt("quantity_in_stock"));

                return p;
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
        return null;
    }

    /**
     *
     * @param pid
     * @return
     */
    public Product getProductByID(int pid) {
        String query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + "join category c on p.category_id = c.id where p.id = ?";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOldPrice(rs.getInt("old_price"));
                p.setCurrentPrice(rs.getInt("current_price"));
                p.setAmountOfSold(rs.getInt("amount_of_sold"));
                p.setOrigin(rs.getString("origin"));
                p.setStatus(rs.getString("status"));

                Category c = new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                p.setCate(c);

                p.setDescribe(rs.getString("describe"));
                p.setQuantity_in_stock(rs.getInt("quantity_in_stock"));

                return p;
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
        return null;
    }

    /**
     *
     * @param textSearch
     * @return
     */
    public List<Product> searchByName(String textSearch) {
        List<Product> list = new ArrayList<>();
        String query = "	select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + "	, c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + "	 join category c on p.category_id = c.id where p.title like ?";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + textSearch + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOldPrice(rs.getInt("old_price"));
                p.setCurrentPrice(rs.getInt("current_price"));
                p.setAmountOfSold(rs.getInt("amount_of_sold"));
                p.setOrigin(rs.getString("origin"));
                p.setStatus(rs.getString("status"));

                Category c = new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                p.setCate(c);

                p.setDescribe(rs.getString("describe"));
                p.setQuantity_in_stock(rs.getInt("quantity_in_stock"));

                list.add(p);
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

    public int getAmountOfProduct() {
        String query = "Select count(*) from Product";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
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

    public boolean isProIdExist(int pid) {
        List<Product> list = this.getAllProduct();
        for (Product p : list) {
            if (p.getId() == pid) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param user
     * @param pass
     * @return
     */
    public Account login(String user, String pass) {
        String query = "select * from account\n"
                + "where username = ? and [password] = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            while (rs.next()) {
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

    /**
     *
     * @param user
     * @return
     */
    public boolean isAccountExist(String user) {
        String query = "Select * from ACCOUNT where username = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    /**
     *
     * @param user
     * @param pass
     * @param isAdmin
     * @param isBlock
     * @return
     */
    public boolean signUp(String user, String pass, int isAdmin, int isBlock) {
        String query = "INSERT INTO ACCOUNT (username, [password], isAdmin, isBlock) VALUES (?, ?, ?, ?)";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, isAdmin);
            ps.setInt(4, isBlock);

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

    public List<Product> getProductRelatedCID(int cateID) {
        List<Product> list = new ArrayList<>();
        String query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + "join category c on p.category_id = c.id \n"
                + "where c.id IN (?, 9, 10)";

        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, cateID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setImage(rs.getString("image"));
                p.setTitle(rs.getString("title"));
                p.setOldPrice(rs.getInt("old_price"));
                p.setCurrentPrice(rs.getInt("current_price"));
                p.setAmountOfSold(rs.getInt("amount_of_sold"));
                p.setOrigin(rs.getString("origin"));
                p.setStatus(rs.getString("status"));
                p.setDescribe(rs.getString("describe"));
                p.setQuantity_in_stock(rs.getInt("quantity_in_stock"));

                Category c = new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                p.setCate(c);

                list.add(p);
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
        // Trộn danh sách sản phẩm ngẫu nhiên sau khi lấy dữ liệu từ cơ sở dữ liệu.
        Collections.shuffle(list, new Random());
        List<Product> randList = list.subList(0, 6);
        return randList;
    }

    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO();
//        boolean signup = dao.signUp("jisoo", "1", 1, 0);
//        if (signup) {
//            System.out.println("ok");
//        } else {
//            System.out.println("none");
//        }

//        boolean acc = dao.isAccountExist("rose");
//        Account ac = dao.login("chaeng", "1");
//        Product p = dao.getProductByID("1");
//        List<Account> p = dao.getAllAccount();
//        List<Category> a = dao.getAllCategory();
//        for (Category account : a) {
//            System.out.println(account);
//        }
//Product p = dao.getProductByID(1);
//        System.out.println(p);
//        int c = dao.getAmountOfProduct();
//        System.out.println(c);

    String cname = dao.getCateNameByPid(29);
        System.out.println(cname);

    }

}
