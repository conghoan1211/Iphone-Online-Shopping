/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dal.CartDAO;
import dal.DAO;
import dal.ManagerDAO;
import entity.profile.Account;
import entity.product.Category;
import entity.product.ColorProduct;
import entity.product.Product;
import entity.product.ProductDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateProServlet extends HttpServlet {

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

        String id_raw = request.getParameter("pid");
        DAO dao = new DAO();
        ManagerDAO mdao = new ManagerDAO();
        try {
            int id = Integer.parseInt(id_raw);
            Product p = dao.getProductByID(id);
            ProductDetail pd = mdao.getProductDetailById(id);
            request.setAttribute("pd", pd);
            request.setAttribute("detail", p);
        } catch (NumberFormatException e) {
        }

        List<Category> listC = dao.getAllCategory();

        request.setAttribute("listC", listC);
        request.getRequestDispatcher("update.jsp").forward(request, response);
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

        String pid = request.getParameter("id");
        int productID = Integer.parseInt(pid);
        String ptitle = request.getParameter("title");
        String pImage = request.getParameter("image");
        String pDescribe = request.getParameter("describe");
        String pPrice_raw = request.getParameter("price");
        String pStock_raw = request.getParameter("stock");
        String pOrigin = request.getParameter("address");
        String pCategory_raw = request.getParameter("category");
        String pStatus = request.getParameter("status");
        String[] nameColor = request.getParameterValues("color");

        String detail_img1 = request.getParameter("detail_img1");
        String detail_img2 = request.getParameter("detail_img2");
        String detail_img3 = request.getParameter("detail_img3");

        int pCurrentPrice = Integer.parseInt(pPrice_raw);
        int pOldPrice = pCurrentPrice + (int) (pCurrentPrice * (0.2));
        int pStock = Integer.parseInt(pStock_raw);
        ManagerDAO mDao = new ManagerDAO();

        DAO dao = new DAO();
        CartDAO cdao = new CartDAO();
        List<ColorProduct> listColor = new ArrayList<>();
        String ms = "";
        boolean update;
        try {
            if (ptitle.length() < 35 || ptitle.length() > 120) {
                ms = "Độ dài tiêu đề không hợp lệ. Vui lòng nhập trong khoảng 34 dến 100 ký tự.";
            } else if (pDescribe.isEmpty() || pDescribe.length() > 2000) {
                ms = "Độ dài miêu tả không hợp lệ. Vui lòng nhập ít nhất 34 ký tự và tối đa 2000 ký tự";
            } else if (pCategory_raw == null || pCategory_raw.equals("-1")) {
                ms = "Vui lòng chọn thể loại.";
            } else if (pStatus == null || pStatus.equals("-1")) {
                ms = "Vui lòng chọn Status.";
            } else if (nameColor == null) {
                ms = "Vui lòng chọn màu sắc sản phẩm.";
            } else {
                for (String c : nameColor) {
                    ColorProduct cp = cdao.getColorById(Integer.parseInt(c));
                    ColorProduct cp1 = new ColorProduct(cp.getColor_id(), cp.getColor_name());
                    listColor.add(cp1);
                }

                int pCategory = Integer.parseInt(pCategory_raw);
                Product oldP = dao.getProductByID(pid);
                oldP.setListColor(listColor);
                update = mDao.updateProduct(productID, pCategory, pImage, ptitle, pOldPrice, pCurrentPrice,
                        pOrigin, pStatus, pStock, pDescribe, oldP);

                boolean updatePD = mDao.updateProductDetail(productID, detail_img1, detail_img2, detail_img3);
                if (update && updatePD) {
                    ms = "Sản phẩm đã được cập nhật thành công.";
                } else {
                    ms = "Lỗi chèn dữ liệu. Vui lòng thực hiện lại.";
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {

            List<Category> listC = dao.getAllCategory();
            request.setAttribute("listC", listC);
            request.setAttribute("message__error", ms);

            request.setAttribute("title", ptitle);
            request.setAttribute("describe", pDescribe);
            request.setAttribute("image", pImage);
            request.setAttribute("origin", pOrigin);
            request.setAttribute("stock", pStock);

            List<Product> list = dao.getAllProduct();
            request.setAttribute("listP", list);
            request.getRequestDispatcher("update.jsp").forward(request, response);
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
