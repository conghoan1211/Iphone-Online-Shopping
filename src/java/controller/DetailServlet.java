/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAO;
import dal.DAO;
import dal.ManagerDAO;
import dal.OrderDAO;
import entity.product.ColorProduct;
import entity.Item;
import entity.product.Product;
import entity.product.ProductDetail;
import entity.product.Product_Variant;
import entity.profile.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import service.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {

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
        String id = request.getParameter("pid");
        DAO dao = new DAO();
        ManagerDAO mdao = new ManagerDAO();
        Product p = dao.getProductByID(id);

        try {
            int cid = p.getCate().getCid();
            List<Product> listRelate = dao.getProductRelatedCID(cid);
            request.setAttribute("listRelated", listRelate);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<ColorProduct> listColor = new ArrayList<>();
        int proid = Integer.parseInt(id);
        ProductDetail pd = mdao.getProductDetailById(proid);
        request.setAttribute("pd", pd);
        CartDAO cdao = new CartDAO();
        List<Product_Variant> listCol = cdao.getAllProVariantByid(proid);

        for (Product_Variant c : listCol) {
            ColorProduct cp = cdao.getColorById(c.getColor_id());
            ColorProduct cp1 = new ColorProduct(cp.getColor_id(), cp.getColor_name());
            listColor.add(cp1);
        }
        request.setAttribute("listColor", listColor);

        // list all feedback with number rate 
        OrderDAO od = new OrderDAO();
        String rate_raw = request.getParameter("rate");
        if (rate_raw == null || rate_raw.equals("0")) {
            rate_raw = "0";
        }
        int rate = 0;
        try {
            rate = Integer.parseInt(rate_raw);
            if (rate == 0) {
                List<Feedback> listFb = od.getAllFeedback(id);
                request.setAttribute("feedB", listFb);
            } else {
                List<Feedback> listRateFb = od.getFeedbacksByRate(id, rate);
                request.setAttribute("feedB", listRateFb);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        request.setAttribute("tag", rate);

        // Push amount of product up cart
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

        request.setAttribute("detail", p);
        request.getRequestDispatcher("details.jsp").forward(request, response);

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

        //this code to save product up cookie
        List<Product> listProduct = dao.getAllProduct();
        Cookie[] cookies = request.getCookies();
        String txt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    txt += cookie.getValue();
                    cookie.setMaxAge(0); // Xóa cookie cũ
                    response.addCookie(cookie);
                }
            }
        }
        String color = request.getParameter("selectedColor");
        String number = request.getParameter("number");
        String id = request.getParameter("pid");
        if (txt.isEmpty()) {
            txt = id + ":" + number + ":" + color;
        } else {
            txt += "-" + id + ":" + number + ":" + color;
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(60 * 60 * 24); // Đặt thời hạn cho cookie
        response.addCookie(c);
        response.sendRedirect("detail?pid=" + id);

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
