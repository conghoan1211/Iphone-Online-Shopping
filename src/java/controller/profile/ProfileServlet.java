/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import dal.DAO;
import dal.ProfileDAO;
import entity.profile.AccAddress;
import entity.profile.AccDetail;
import entity.profile.Account;
import entity.Item;
import entity.product.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import service.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
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

        DAO dao = new DAO();
        List<Product> listProduct = dao.getAllProduct();
        Cookie[] arr = request.getCookies();
        String txt = "";

        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart();
        cart.initializeCartFromText(txt, listProduct);
        request.setAttribute("cart", cart);
        List<Item> listItem = cart.getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("amount", amountItem);

        ProfileDAO pdao = new ProfileDAO();
        AccDetail accDe = pdao.getAccountDetail(acc.getUsername());

        try {
            if (accDe != null) {
                request.setAttribute("AccDetail", accDe);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("profile.jsp");
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
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob_raw = request.getParameter("dob");

//        Date dob = Date.valueOf(dob_raw);
        String ms = "";

        try {
            Date dob = (dob_raw == null || dob_raw.equals(""))
                    ? null : Date.valueOf(dob_raw);

            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");    

            if (acc == null) {
                request.setAttribute("ms", "message");
                request.setAttribute("message", "Vui lòng đăng nhập tài khoản trước.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                String username = acc.getUsername();

                ProfileDAO pdao = new ProfileDAO();
                boolean existAccD = pdao.isAccDetailExist(username);
                if (existAccD) {
                    boolean update = pdao.updateProfile(username, nickname, email, phone, gender, dob);
                    if (update) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                } else {
                    boolean insert = pdao.insertProfile(username, nickname, email, phone, gender, dob);
                    if (insert) {
                        ms = "Cập nhật hồ sơ thành công.";
                    } else {
                        ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                    }
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("msSuccess", ms);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
