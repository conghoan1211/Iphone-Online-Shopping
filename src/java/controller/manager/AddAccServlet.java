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
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "ManaAccServlet", urlPatterns = {"/add.account"})
public class AddAccServlet extends HttpServlet {

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

//        String accid_raw = request.getParameter("accid");
        DAO dao = new DAO();
//        ManagerDAO mdao = new ManagerDAO();
//        try {
//            int accID = Integer.parseInt(accid_raw);
//            Account account = mdao.getAccountByUser(accID);
//            if (account != null) {
//                request.setAttribute("acca", account);
//
//            }
//
//        } catch (NumberFormatException e) {
//        }

        List<Account> p = dao.getAllAccount();

        request.setAttribute("listA", p);
        request.getRequestDispatcher("managerAcc.jsp").forward(request, response);
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

        String accUser = request.getParameter("username");
        String accPass = request.getParameter("password");
        String isAdmin_raw = request.getParameter("isAdmin");
        String isBlock_raw = request.getParameter("isBlock");

        String ms = "";
        try {
            int isAdmin = Integer.parseInt(isAdmin_raw);
            int isBlock = Integer.parseInt(isBlock_raw);

            DAO dao = new DAO();
            ProfileDAO pdao = new ProfileDAO();

            boolean existAccD = pdao.isAccDetailExist(accUser);
            if (existAccD) {
                ms = "Tên tài khoản đã tồn tại.";
            } else {
                boolean add = dao.signUp(accUser, accPass, isAdmin, isBlock);
                if (add) {
                    ms = "Đăng kí thành công.";
                } else {
                    ms = "Đăng kí không thành công.";
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("managerAcc.jsp").forward(request, response);

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
