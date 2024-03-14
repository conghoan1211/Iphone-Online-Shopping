/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.IConstant;
import dal.DAO;
import dal.SortPDAO;
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
import java.util.List;
import service.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

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

        DAO dao = new DAO();

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
        int amountItem = 0;
        if (listItem != null) {
            amountItem = listItem.size();
        } else {
            amountItem = 0;
        }
        request.setAttribute("cart", cart);
        request.setAttribute("amount", amountItem);
        //****//

        List<Category> listC = dao.getAllCategory();

        SortPDAO sort = new SortPDAO();
        List<TypeSorted> listSort = sort.getAllTypeSorted();

        /*get all product by filter */
        String[] origin = request.getParameterValues("origin");
        String other = request.getParameter("other");
        String[] cate = request.getParameterValues("category");
        String priceFrom = request.getParameter("pricefrom");
        String priceTo = request.getParameter("priceto");

        try {
            if (priceFrom.isEmpty()) {
                priceFrom = null;
            }
            if (priceTo.isEmpty()) {
                priceTo = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // pagination 
//        String index_Page = request.getParameter("index");
//        if (index_Page == null) {
//            index_Page = "1";
//        }
//        int index = Integer.parseInt(index_Page);
        List<Product> listFilter = sort.getFilterProduct(origin, other, cate, priceFrom, priceTo);
//        int count = listFilter.size();
//        int endPage = count / 15;
//        if (endPage % 15 != 0) {
//            endPage++;
//        }
//        request.setAttribute("endP", endPage);
//        request.setAttribute("tag", index);

//        tag used to mark that categoty ID active
        String cateID = request.getParameter("cid");

        if (cateID != null) {
            List<Product> listByCID = dao.getProductByCID(cateID);
            request.setAttribute("listP", listByCID);
        } else {
            request.setAttribute("listP", listFilter);
        }

        request.setAttribute("tag", cateID);
        request.setAttribute("listSorted", listSort);
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
