/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.product.Category;
import entity.product.Product;
import entity.TypeSorted;
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
public class SortPDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     *
     * @return
     */
    public List<TypeSorted> getAllTypeSorted() {
        List<TypeSorted> list = new ArrayList<>();
        String query = "Select TOP 3 * from Sorted";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new TypeSorted(rs.getInt(1),
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

    public List<Product> sortProductBySortID(int sortId, int index) {
        List<Product> list = new ArrayList<>();

        String query = "select TOP 10 p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + "join category c on p.category_id = c.id ";

        switch (sortId) {
            case 1:
                query += " Where p.status = 'sale'";
                break;
            case 2:
                query += " ORDER BY p.id DESC";
                break;
            case 3:
                query += " ORDER BY p.amount_of_sold DESC";
                break;
            case 4:
                query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                        + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                        + "join category c on p.category_id = c.id ORDER BY p.current_price ASC "
                        + "offset ? ROWS FETCH NEXT 15 ROWS ONLY";
                break;
            case 5:
                query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                        + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                        + "join category c on p.category_id = c.id ORDER BY p.current_price DESC "
                        + "offset ? ROWS FETCH NEXT 15 ROWS ONLY";
                break;
            default:
                break;
        }

        try {
            ps = connection.prepareStatement(query);
            if (sortId == 4 || sortId == 5) {
                ps.setInt(1, (index - 1) * 15);
            }
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

    public List<Product> getProductByPageNum(int index) {
        List<Product> list = new ArrayList<>();
        String query = "select p.id, p.image,p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status\n"
                + ", c.id as cid, c.name as cname, p.describe, p.quantity_in_stock from product as p\n"
                + "join category c on p.category_id = c.id "
                + "ORDER BY id\n"
                + "offset ? ROWS FETCH NEXT 15 ROWS ONLY";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 15);
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
        Collections.shuffle(list, new Random());
        return list;
    }

    public List<Product> getFilterProduct(String[] origin, String other, String[] cate, String priceFrom, String priceTo) {
        List<Product> list = new ArrayList<>();

        StringBuilder query = new StringBuilder();
        query.append("SELECT p.id, p.image, p.title, p.old_price, p.current_price, p.amount_of_sold, p.origin, p.status, ");
        query.append("c.id AS cid, c.name AS cname, p.describe, p.quantity_in_stock ");
        query.append("FROM product AS p ");
        query.append("JOIN category c ON p.category_id = c.id ");

        boolean whereClauseNeeded = false;

        if (origin != null || other != null || cate != null || (priceFrom != null && priceTo != null)) {
            query.append("WHERE ");

            if (origin != null) {
                for (int i = 0; i < origin.length; i++) {
                    query.append("origin = N'").append(origin[i]).append("'");
                    if (i < origin.length - 1) {
                        query.append(" OR ");
                    }
                }
                whereClauseNeeded = true;
            }

            if (other != null) {
                if (whereClauseNeeded) {
                    query.append(" AND ");
                }
                query.append("(origin != N'Hà Nội' AND origin != N'TP.Hồ Chí Minh' AND origin != N'Hưng Yên' AND origin != N'Thái Bình')");
                whereClauseNeeded = true;
            }

            if (cate != null) {
                if (whereClauseNeeded) {
                    query.append(" AND ");
                }
                if (cate.length == 1) {
                    for (String cate1 : cate) {
                        if (cate1.equals("phone")) {
                            query.append("category_id IN (15, 14, 13, 12, 11)");
                        }
                        if (cate1.equals("assessory")) {
                            query.append("category_id IN (10, 9, 8)");
                        }
                    }
                } else if (cate.length > 1) {
                    query.append("category_id IN (15, 14, 13, 12, 11, 10, 9, 8)");
                }
                whereClauseNeeded = true;
            }
        }
//        if (origin != null || other != null || cate != null ) {
            if (priceFrom != null && priceTo != null) {
                if (whereClauseNeeded) {
                    query.append(" AND ");
                }
                query.append(" current_price BETWEEN ").append(priceFrom).append(" AND ").append(priceTo);
            } else if (priceFrom != null && priceTo == null) {
//                if (whereClauseNeeded) {
//                    query.append(" AND ");
//                }
                query.append(" and current_price > ").append(priceFrom);
            }else  if (priceFrom == null && priceTo != null) {
//                if (whereClauseNeeded) {
//                    query.append(" AND ");
//                }
                query.append(" and current_price < ").append(priceTo);
            }
//        }

//        query.append(" ORDER BY id\n");
//        query.append("OFFSET ? ROWS FETCH NEXT 15 ROWS ONLY");
        System.out.println("query =" + query);
        try {
            ps = connection.prepareStatement(query.toString());
//            ps.setInt(1, (index - 1) * 15);
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
        SortPDAO sort = new SortPDAO();
        String[] s = {"Hà Nội"};
        String[] p = {"assessory"};
        List<Product> list = sort.getFilterProduct(null, null, p, "30000", "300000");
        for (Product typeSorted : list) {
            System.out.println(typeSorted);
        }
    }
}
