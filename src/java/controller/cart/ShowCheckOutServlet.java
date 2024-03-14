/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.cart;

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
public class ShowCheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet ShowCheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCheckOutServlet at " + request.getContextPath() + "</h1>");
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

        List<Item> listItem = cart.getItems();
        int amountItem;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("amount", amountItem);
        request.setAttribute("cart", cart);
        // submit form checkout 
        String type_checkout = request.getParameter("type_checkout");

        try {
            int type = Integer.parseInt(type_checkout);
            if (type == 0) {
                request.setAttribute("checkMoney", cart.getTotalMoney() + 40000);
            } else {
                request.setAttribute("checkMoney", cart.getTotalMoney() + 40000 - 15000);
            }
            request.setAttribute("type", type);
        } catch (NumberFormatException e) {
        }
      
        ProfileDAO pdao = new ProfileDAO();
        AccDetail accDe = pdao.getAccountDetail(acc.getUsername());
        AccAddress accAdd = pdao.getAccountAdress(acc.getUsername());

        try {
            if (accDe != null) {
                request.setAttribute("AccDetail", accDe);
                request.setAttribute("AccAddr", accAdd);

                request.getRequestDispatcher("checkout.jsp").forward(request, response);
            } else {
                response.sendRedirect("checkout.jsp");
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
        String detailName = request.getParameter("logName");
        String detailEmail = request.getParameter("logEmail");
        String detailPhone = request.getParameter("logPhone");
        String accAddr = request.getParameter("logAddress");
        String accAddrDe = request.getParameter("logDetailAddr");
        String logNote = request.getParameter("logNote");

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        String ms = "";
        if (acc == null) {
            request.setAttribute("ms", "message");
            request.setAttribute("message", "Vui lòng đăng nhập tài khoản trước.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            String username = acc.getUsername();
            ProfileDAO pdao = new ProfileDAO();

            AccDetail accde = pdao.getAccountDetail(username);
            AccAddress accaddr = pdao.getAccountAdress(username);

            boolean existAccD = pdao.isAccDetailExist(username);
            if (existAccD) {
                boolean update = pdao.updateProfile(username, detailName, detailEmail, detailPhone, accde.getGender(), accde.getDob());
                if (update) {
                    ms = "Cập nhật hồ sơ thành công.";
                } else {
                    ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                }
            } else {
                boolean insert = pdao.updateProfile(username, detailName, detailEmail, detailPhone, accde.getGender(), accde.getDob());
                if (insert) {
                    ms = "Cập nhật hồ sơ thành công.";
                } else {
                    ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                }
            }

            boolean existAcc = pdao.isAccAddressExist(acc.getUsername());
            if (existAcc) {
                boolean update = pdao.updateAddress(acc.getUsername(), accde.getNickname(), detailPhone, accAddr, accAddrDe, accaddr.getStatus());
                if (update) {
                    ms = "Cập nhật địa chỉ thành công.";
                } else {
                    ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                }
            } else {
                boolean insert = pdao.updateAddress(acc.getUsername(), accde.getNickname(), detailPhone, accAddr, accAddrDe, accaddr.getStatus());
                if (insert) {
                    ms = "Cập nhật địa chỉ thành công.";
                } else {
                    ms = "Có lỗi xảy ra. Vui lòng thực hiên lại.";
                }
            }

            request.setAttribute("msSuccess", ms);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
