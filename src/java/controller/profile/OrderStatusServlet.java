/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import constant.IConstant;
import dal.DAO;
import dal.OrderDAO;
import entity.OrderStatus;
import entity.product.Product;
import entity.profile.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class OrderStatusServlet extends HttpServlet {

    private final static String ORDER_PAGE = "orders.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderStatusServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String returnUrl = request.getHeader("referer"); // Lấy địa chỉ URL trước đó
        String url = ORDER_PAGE;

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Vui lòng đăng nhập tài khoản trước.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        String action = request.getParameter("action");
        String pid = request.getParameter("pid");
        String color = request.getParameter("color");
        String orderid_raw = request.getParameter("orderid");
        DAO dao = new DAO();
        OrderDAO od = new OrderDAO();

        Product p = new Product();
        if (pid != null) {
            p = dao.getProductByID(pid);
        }

        List<OrderStatus> listOrder = new ArrayList<>();
        String type_order = request.getParameter("type");
        if (type_order == null) {
            type_order = "0";
        }
        if (orderid_raw == null) {
            orderid_raw = "0";
        }
        int type = 0, orderid = 0;
        try {
            orderid = Integer.parseInt(orderid_raw);
            type = Integer.parseInt(type_order);
            if (action != null && action.equals("received")) {
                boolean set = od.setStatusOrder(acc.getUsername(), orderid, IConstant.IS_DELIVERED);
            }
            if (action != null && action.equals("cancel")) {
                boolean set = od.setStatusOrder(acc.getUsername(), orderid, IConstant.IS_CANCELED);
            }
            switch (type) {
                case 0:
                    listOrder = od.getAllOrders(acc.getUsername());
                    break;
                case 1:
                    listOrder = od.getAllAcceptedOrders(acc.getUsername(), IConstant.DEFAUT);
                    break;
                case 2:
                    listOrder = od.getAllDeliveredOrders(acc.getUsername(), IConstant.DEFAUT);
                    break;
                case 3:
                    listOrder = od.getAllDeliveredOrders(acc.getUsername(), IConstant.IS_DELIVERED);
                    break;
                case 4:
                    listOrder = od.getAllFeedbackOrders(acc.getUsername(), IConstant.DEFAUT);
                    break;
                case 5:
                    listOrder = od.getAllAcceptedOrders(acc.getUsername(), IConstant.IS_CANCELED);
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("tag", type);
            request.setAttribute("p", p);
            request.setAttribute("color", color);
            request.setAttribute("order_id", orderid);
            request.setAttribute("listOrder", listOrder);
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO dao = new DAO();

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        String pcolor = request.getParameter("pcolor");
        String orderid_raw = request.getParameter("order_id");
        String pid_raw = request.getParameter("proid");
        String selectedRating_raw = request.getParameter("selectedRating");
        String describe = request.getParameter("describe");
        String feature = request.getParameter("feature");
        String standard = request.getParameter("standard");
        String content = request.getParameter("content");
        String image = request.getParameter("image");
        String display_raw = request.getParameter("display");

        if (display_raw == null) {
            display_raw = "0";
        }
        String ms = "";
        int display = 0;
        try {
            int orderid = Integer.parseInt(orderid_raw);
            int pid = Integer.parseInt(pid_raw);
            int rating = Integer.parseInt(selectedRating_raw);
            display = Integer.parseInt(display_raw);
            String cname = dao.getCateNameByPid(pid);

            OrderDAO od = new OrderDAO();
            boolean feedback = od.feedbackByUser(acc.getUsername(), cname, pcolor, pid, rating, describe, feature, standard, content, image, display);
            if (feedback) {
                boolean setStatus = od.setStatusOrder(acc.getUsername(), orderid, IConstant.IS_FEEDBACKED);
                request.setAttribute("ms", "Bạn đã gửi đánh giá thành công.");
            } else {
                request.setAttribute("ms", "Đánh giá không thành công.");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("describe", describe);
            request.getRequestDispatcher(ORDER_PAGE).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
