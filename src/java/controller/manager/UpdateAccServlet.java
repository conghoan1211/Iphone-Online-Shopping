/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.DAO;
import dal.ManagerDAO;
import dal.ProfileDAO;
import entity.profile.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateAccServlet", urlPatterns = {"/update.account"})
public class UpdateAccServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateAccServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAccServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Session đã kết thúc. Vui lòng đăng nhập tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } else if (acc.getIsAdmin() != 1) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Tài khoản của bạn không được phép vào trang này.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String accid_raw = request.getParameter("accID");
        ManagerDAO mdao = new ManagerDAO();
        try {
            int accID = Integer.parseInt(accid_raw);
            Account acca = mdao.getAccountByUser(accID);
            if (acca != null) {
                request.setAttribute("acca", acca);
            } else {
                request.setAttribute("ms", "Tài khoản không tồn tại.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("updateAcc.jsp").forward(request, response);
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
        String accid_raw = request.getParameter("acc_id");
        String accUser = request.getParameter("username");
        String accPass = request.getParameter("password");
        String isAdmin_raw = request.getParameter("isAdmin");
        String isBlock_raw = request.getParameter("isBlock");

        String ms = "";
        try {
            int accID = Integer.parseInt(accid_raw);
            int isAdmin = Integer.parseInt(isAdmin_raw);
            int isBlock = Integer.parseInt(isBlock_raw);

            ManagerDAO mdao = new ManagerDAO();
            boolean update = mdao.updateAccount(accID, accUser, accPass, isAdmin, isBlock);
            if (update) {
                ms = "Cập nhật thành công.";
            } else {
                ms = "Cập nhật không thành công.";
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("message__del", ms);
            request.getRequestDispatcher("updateAcc.jsp").forward(request, response);
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
