/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import dal.DAO;
import dal.ProfileDAO;
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
import java.util.List;
import service.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "PasswordServlet", urlPatterns = {"/password"})
public class PasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet PasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("password.jsp").forward(request, response);
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
        String oldPass = request.getParameter("old_pass");
        String newPass = request.getParameter("new_pass");
        String newRepass = request.getParameter("new_repass");
        String password = "";

        String msOldpass = "";
        String msNewpass = "";
        String msNewrepass = "";
        String msSuccess = "";
        String syntax = "none";
        String syntaxOld = "none";
        String syntaxNew = "none";

        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");

            if (acc == null) {
                request.setAttribute("ms", "message");
                request.setAttribute("message", "Vui lòng đăng nhập tài khoản trước.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                password = acc.getPassword();

                if (!oldPass.equals(password)) {
                    msOldpass = "Mật khẩu cũ nhập chưa chính xác";
                    syntaxOld = "";
                    request.setAttribute("red", "red");
                } else if (newPass.length() < 6) {
                    msNewpass = "Mật khẩu mới phải dài 8-16 ký tự, chứa ít nhất một ký tự viết hoa \n"
                            + "hoặc 1 ký tự số hoặc dấu câu thông thường";
                    syntaxNew = "";
                    request.setAttribute("red", "red");
                } else if (newPass.equalsIgnoreCase(password)) {
                    msNewpass = "Mật khẩu mới phải khác mật khẩu hiện tại.";
                    syntaxNew = "";
                    request.setAttribute("red", "red");
                } else if (!newPass.equals(newRepass)) {
                    msNewrepass = "Mật khẩu và Mật khẩu xác nhận không giống nhau";
                    syntax = "";
                    request.setAttribute("red", "red");
                } else {
                    ProfileDAO proDAO = new ProfileDAO();
                    boolean change = proDAO.changePassword(acc.getUsername(), newPass);
                    if (change) {
                        msSuccess = "Chúc mừng bạn đã thay đổi mật khẩu thành công.";
                        syntax = "none";
                        oldPass = "";
                        newPass = "";
                        newRepass = "";
                    } else {
                        msSuccess = "Có lỗi xảy ra. Vui lòng thực hiện lại";
                        syntax = "none";
                    }
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        } finally {
        }
        request.setAttribute("syntaxNew", syntaxNew);
        request.setAttribute("syntaxOld", syntaxOld);
        request.setAttribute("syntax", syntax);

        request.setAttribute("msOldpass", msOldpass);
        request.setAttribute("msNewpass", msNewpass);
        request.setAttribute("msNewrepass", msNewrepass);
        request.setAttribute("msSuccess", msSuccess);

        request.setAttribute("oldPass", oldPass);
        request.setAttribute("newPass", newPass);
        request.setAttribute("newRepass", newRepass);

        request.getRequestDispatcher("password.jsp").forward(request, response);
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
