/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import dal.ProfileDAO;
import dal.SortPDAO;
import entity.profile.AccDetail;
import entity.profile.Account;
import entity.product.Category;
import entity.Item;
import entity.product.Product;
import entity.TypeSorted;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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

        // load accDetail
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        try {
            ProfileDAO pdao = new ProfileDAO();
            AccDetail accd = pdao.getAccountDetail(acc.getUsername());
            if (accd != null) {
                session.setAttribute("AccDetail", accd);
            }
        } catch (Exception e) {
        }

        //
        DAO PDAO = new DAO();
        List<Category> listC = PDAO.getAllCategory();
        SortPDAO sort = new SortPDAO();
        List<TypeSorted> listSorted = sort.getAllTypeSorted();

        List<Product> listProduct = PDAO.getAllProduct();

        // filter pagination
        String index_Page = request.getParameter("index");
        if (index_Page == null) {
            index_Page = "1";
        }
        int index = Integer.parseInt(index_Page);

        int count = listProduct.size();
        int endPage = count / 15;
        if (endPage % 15 != 0) {
            endPage++;
        }
        List<Product> listP = sort.getProductByPageNum(index);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);

        // get product by sort id 
        String sortID_raw = request.getParameter("sortID");
        if (sortID_raw == null || sortID_raw.isEmpty()) {
            request.setAttribute("listP", listP);
        } else {
            int sortID = Integer.parseInt(sortID_raw);
            List<Product> list = sort.sortProductBySortID(sortID, index);
            request.setAttribute("listP", list);
        }

        // push amount òf product up cart
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

        request.setAttribute("listSorted", listSorted);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
        processRequest(request, response);
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
