/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.IConstant;
import entity.OrderStatus;
import entity.profile.Account;
import entity.profile.Feedback;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class OrderDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    private List<OrderStatus> listOrder;

    public OrderDAO() {
        this.listOrder = new ArrayList<>();
    }

    public OrderDAO(List<OrderStatus> listOrder) {
        this.listOrder = listOrder;
    }

    public List<OrderStatus> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderStatus> listOrder) {
        this.listOrder = listOrder;
    }

    public List getAllOrders(String username) {
        String query = "select o.order_id, p.id, p.image, p.title, c.name as cname, od.quantity, p.old_price, p.current_price, od.total_price,\n"
                + "pc.color_name, o.delivered, o.order_date, o.accepted, o.feedback, p.status\n"
                + "from OrderDetail od join orders o on od.order_id = o.order_id\n"
                + "join Product p on p.id = od.product_id\n"
                + "join Category c on c.id = p.category_id\n"
                + "join Product_Color pc on od.color_id = pc.colorid\n"
                + "where color_id IS NOT NULL and username = ? "
                + "ORDER BY order_id DESC";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.listOrder.add(new OrderStatus(rs.getInt("order_id"),
                        rs.getInt("id"),
                        rs.getInt("quantity"), rs.getInt("old_price"),
                        rs.getInt("current_price"),
                        rs.getInt("total_price"),
                        rs.getString("image"),
                        rs.getString("title"),
                        rs.getString("cname"),
                        rs.getString("status"),
                        rs.getString("color_name"),
                        rs.getString("order_date"),
                        rs.getInt("delivered"),
                        rs.getInt("accepted"),
                        rs.getInt("feedback")));
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
        return listOrder;
    }

    /**
     * get all order that accepted by admin
     *
     * @param username
     * @param TYPE_ACCEPTED if type = 1 is accepted, type = 0 is not accepted,
     * type = -1 is canceled
     * @return
     */
    public List getAllAcceptedOrders(String username, final int TYPE_ACCEPTED) {
        List<OrderStatus> listOS = this.getAllOrders(username);
        List<OrderStatus> listOSbyAccepted = new ArrayList<>();
        for (OrderStatus os : listOS) {
            if (TYPE_ACCEPTED == IConstant.IS_CANCELED) {
                if (os.getAccepted() == IConstant.IS_CANCELED || os.getAccepted() == IConstant.IS_NO_ACCEPTED_BY_ADMIN) {
                    listOSbyAccepted.add(os);
                }
            } else {
                if (os.getAccepted() == IConstant.DEFAUT) {
                    listOSbyAccepted.add(os);
                }
            }
        }
        return listOSbyAccepted;
    }

    /**
     *
     *
     * @param username
     * @param TYPE_DELIVERED if type = 0 is delivering, type = 1 is complete
     * delivered
     * @return
     */
    public List getAllDeliveredOrders(String username, final int TYPE_DELIVERED) {
        List<OrderStatus> listOS = this.getAllOrders(username);
        List<OrderStatus> listOSbyDelivered = new ArrayList<>();
        for (OrderStatus os : listOS) {
            if (os.getDelivered() == TYPE_DELIVERED && os.getAccepted() == IConstant.IS_ACCEPTED) {
                listOSbyDelivered.add(os);
            }
        }
        return listOSbyDelivered;
    }

    public List getAllFeedbackOrders(String username, final int TYPE_FEEDBACK) {
        List<OrderStatus> listOS = this.getAllOrders(username);
        List<OrderStatus> listOSbyFeedback = new ArrayList<>();
        for (OrderStatus os : listOS) {
            if (os.getAccepted() == IConstant.IS_ACCEPTED && os.getDelivered() == IConstant.IS_DELIVERED && os.getFeedback() == TYPE_FEEDBACK) {
                listOSbyFeedback.add(os);
            }
        }
        return listOSbyFeedback;
    }

    /**
     *
     * @param username
     * @param cname
     * @param colorName
     * @param pid
     * @param rating
     * @param describe
     * @param feature
     * @param standard
     * @param content
     * @param image
     * @param display
     * @return
     */
    public boolean feedbackByUser(String username, String cname, String colorName, int pid, int rating, String describe, String feature,
            String standard, String content, String image, int display) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = currentDateTime.format(formatter);

        String query = "INSERT INTO [dbo].[FeedBack]\n"
                + " ([product_id],[cate_name],[color_name],[username] ,[feed_date] ,[rate]\n"
                + " ,[describe] ,[feature] ,[standard] ,[content]\n"
                + " ,[image] ,[display])\n"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setString(2, cname);
            ps.setString(3, colorName);
            ps.setString(4, username);
            ps.setString(5, dateTime);
            ps.setInt(6, rating);
            ps.setString(7, describe);
            ps.setString(8, feature);
            ps.setString(9, standard);
            ps.setString(10, content);
            ps.setString(11, image);
            ps.setInt(12, display);
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

    public boolean setStatusOrder(String username, int orderid, final int TYPE_STATUS) {
        String query = "UPDATE Orders ";

        switch (TYPE_STATUS) {
            case IConstant.DEFAUT:
                query += "SET [feedback] = " + IConstant.DEFAUT;
                break;
            case IConstant.IS_CANCELED:
                query += "SET [accepted] = " + IConstant.IS_CANCELED;
                break;
            case IConstant.IS_NO_ACCEPTED_BY_ADMIN:
                query += "SET [accepted] = " + IConstant.IS_NO_ACCEPTED_BY_ADMIN;
                break;
            case IConstant.IS_ACCEPTED:
                query += "SET [accepted] = " + IConstant.IS_ACCEPTED;
                break;
            case IConstant.IS_DELIVERED:
                query += "SET [delivered] = " + IConstant.IS_DELIVERED;
                break;
            case IConstant.IS_FEEDBACKED:
                query += "SET [feedback] = " + IConstant.IS_FEEDBACKED;
                break;
            case IConstant.IS_PURCHASED:
                query += "SET [delivered] = " + IConstant.IS_PURCHASED;
                break;
            default:
                break;
        }
        query += " WHERE order_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderid);
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

    public List<Feedback> getAllFeedback(String pid) {
        List<Feedback> list = new ArrayList<>();
        String query = "select * from FeedBack where product_id = ? Order By feed_id DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Feedback(rs.getInt("product_id"),
                        rs.getInt("rate"),
                        rs.getInt("display"),
                        rs.getString("username"),
                        rs.getString("cate_name"),
                        rs.getString("color_name"),
                        rs.getString("feed_date"),
                        rs.getString("describe"),
                        rs.getString("feature"),
                        rs.getString("standard"),
                        rs.getString("content"),
                        rs.getString("image")));
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

    public List<Feedback> getFeedbacksByRate(String pid, int rate) {
        List<Feedback> listRate = new ArrayList<>();
        List<Feedback> list = this.getAllFeedback(pid);
        for (Feedback feedback : list) {
            if (feedback.getRate() == rate) {
                listRate.add(feedback);
            }
        }
        return listRate;
    }

    public static void main(String[] args) {
        OrderDAO od = new OrderDAO();
        List<OrderStatus> list = od.getAllOrders("Phạm Hoan");
        List<Feedback> list1 = od.getAllFeedback("5");

//        pdao.updateProfile("admin", ac.getNickname(), "haon@adas", ac.getPhone()
//                , ac.getGender(), dobFrom);
        for (OrderStatus orderDetail : list) {
            System.out.println(orderDetail);
        }
//        boolean a = od.feedbackByUser("jennie", "iphone13","", 5, 4, "a", "", "", "", "", 1);
//        if (a) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
    }

}
