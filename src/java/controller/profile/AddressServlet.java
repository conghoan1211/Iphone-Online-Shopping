/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import dal.ProfileDAO;
import entity.profile.AccAddress;
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
@WebServlet(name = "AddressServlet", urlPatterns = {"/address"})
public class AddressServlet extends HttpServlet {

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
            out.println("<title>Servlet AddressServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressServlet at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("message", "Vui lòng đăng nhập tài khoản trước.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        ProfileDAO pdao = new ProfileDAO();
        AccAddress accAdd = pdao.getAccountAdress(acc.getUsername());
        

        try {
            if (accAdd != null) {
                request.setAttribute("AccAddr", accAdd);
                request.getRequestDispatcher("address.jsp").forward(request, response);
            } else {
                response.sendRedirect("address.jsp");
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

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
        String nickname = request.getParameter("nickname");
        String phone = request.getParameter("phone");
        String addr = request.getParameter("addr");
        String addrDe = request.getParameter("addrDe");
        String home_raw = request.getParameter("selectedOption");

        String ms = "";
        try {
        
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");

            if (acc == null) {
                request.setAttribute("ms", "message");
                request.setAttribute("message", "Vui lòng đăng nhập tài khoản trước.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                ProfileDAO pdao = new ProfileDAO();
                boolean existAcc = pdao.isAccAddressExist(acc.getUsername());

                if (existAcc) {
                    boolean update = pdao.updateAddress(acc.getUsername(), nickname, phone, addr, addrDe, home_raw);
                    if (update) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = pdao.insertAddress(acc.getUsername(), nickname, phone, addr, addrDe, home_raw);
                    if (insert) {
                        ms = "Cập nhật địa chỉ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        request.setAttribute("msSuccess", ms);
        request.getRequestDispatcher("address.jsp").forward(request, response);

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
