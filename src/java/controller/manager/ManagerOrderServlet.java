/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import constant.IConstant;
import dal.ManagerDAO;
import dal.OrderDAO;
import entity.Orders;
import entity.profile.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author admin
 */
public class ManagerOrderServlet extends HttpServlet {

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
        String orderid_raw = request.getParameter("order_id");
        String username = request.getParameter("username");
        String action = request.getParameter("action");

        OrderDAO od = new OrderDAO();
        int orderid = 0;
        String ms = "";
        try {
            orderid = Integer.parseInt(orderid_raw);
        } catch (NumberFormatException e) {
        }

        if (action.equals("Giao hàng")) {
            boolean set = od.setStatusOrder(username, orderid, IConstant.IS_ACCEPTED);
            if (set) {
                ms = "Đơn hàng đã được chấp nhận được giao.";
            } else {
                ms = "Đã có lỗi xảy ra. Không thể chấp nhận đơn hàng.";
            }
        }
        if (action.equals("Hủy đơn")) {
            boolean set = od.setStatusOrder(username, orderid, IConstant.IS_NO_ACCEPTED_BY_ADMIN);
            if (set) {
                ms = "Đơn hàng đã bị hủy bởi bạn.";
            } else {
                ms = "Đã có lỗi xảy ra. Không thể hủy đơn.";
            }
        }

        request.setAttribute("ms", ms);
        request.getRequestDispatcher("managerOrder.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
//            response.sendRedirect("localhost:9999/suga/login.jsp");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } else if (acc.getIsAdmin() != 1) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Tài khoản của bạn không được phép vào trang này.");
            request.getRequestDispatcher("/suga/login.jsp").forward(request, response);
            return;
        }
        
        ManagerDAO mdao = new ManagerDAO();
        String type_raw = request.getParameter("type");
        String text = request.getParameter("text");
        String select_raw = request.getParameter("select");
        if (select_raw == null || select_raw.isEmpty()) {
            select_raw = "0";
        }
        if (type_raw == null) {
            type_raw = "0";
        }
        int type = 0, select = 0, sizeOfList = 0;
        List<Orders> listOrder = null;

        try {
            type = Integer.parseInt(type_raw);
            select = Integer.parseInt(select_raw);
        } catch (NumberFormatException e) {
        }
        listOrder = mdao.getAllOrders(type_raw, text, select);
        sizeOfList = listOrder.size();

        request.setAttribute("size", sizeOfList);
        request.setAttribute("listO", listOrder);
        request.setAttribute("tag", type);

        request.getRequestDispatcher("managerOrder.jsp").forward(request, response);
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
        processRequest(request, response);
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
