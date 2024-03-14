/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.acc;

import dal.DAO;
import dal.ManagerDAO;
import entity.profile.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String remember = request.getParameter("remember");

        //tao 3 cookie: username, password, remember
        Cookie cu = new Cookie("user", username);
        Cookie cp = new Cookie("pass", password);
        Cookie cr = new Cookie("crem", remember);
        if (remember != null) {
            //co chon
            cu.setMaxAge(60 * 60 * 24 * 7);// 7 ngay
            cp.setMaxAge(60 * 60 * 24 * 7);// 7 ngay
            cr.setMaxAge(60 * 60 * 24 * 7);// 7 ngay
        } else {
            //khong chon
            cu.setMaxAge(0);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        // luu vao browse
        response.addCookie(cu);
        response.addCookie(cp);
        response.addCookie(cr);
        DAO dao = new DAO();

        try {
            Account acc = dao.login(username.trim(), password.trim());
            if (acc == null || username.trim().isEmpty() || password.trim().isEmpty()) {
                request.setAttribute("ms", "message");
                request.setAttribute("message", "Thông tin đăng nhập không chính xác");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else if (acc.getIsBlock() == 1) {
                request.setAttribute("ms", "message");
                request.setAttribute("message", "Tài khoản của bạn đã bị chặn.");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else {
                HttpSession session = request.getSession();
                session.setAttribute("acc", acc);
                session.setMaxInactiveInterval(3000); // set time active of account (seconds)
//            request.getRequestDispatcher("home").forward(request, response);
                response.sendRedirect("home");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        response.sendRedirect("login.jsp");

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
