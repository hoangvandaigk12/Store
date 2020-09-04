/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.servlet;

import dai.cart.CartObj;
import dai.cart.Item;
import dai.dao.AccountDAO;
import dai.dto.OrderErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AD
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {
    private static final String ORDER_ERROR_PAGE = "oder_error.jsp";
    private static final String LOAD_BOOK_SERVLET = "LoadBookServlet";
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
        PrintWriter out = response.getWriter();
        String cusName = request.getParameter("txtCusName");
        String address = request.getParameter("txtAddress");
        String num = request.getParameter("txtPhone");
        OrderErrors errors = new OrderErrors();
        String url = ORDER_ERROR_PAGE;
        int phone = 0;
        try {
            boolean foundErr = false;
            if (cusName.trim().length() < 2 || cusName.trim().length() > 50) {
                foundErr = true;
                errors.setFullnameLenghtErr("2 <= length of Customer Name <= 50");
            }

            if (address.trim().length() < 2 || address.trim().length() > 50) {
                foundErr = true;
                errors.setAddressLenghtErr("2 <= length of Address <= 50");
            }

            if (num.trim().length() < 10 || num.trim().length() > 12) {
                foundErr = true;
                errors.setPhoneLenghtErr("10 <= lenght of Phone <= 12");
            } else {
                try {
                    phone = Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    foundErr = true;
                    errors.setPhoneFormatErr("Phone is integer format!");
                }
            }

            if (foundErr) {
                request.setAttribute("ORDER_ERROR", errors);
            } else {
                AccountDAO dao = new AccountDAO();
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    boolean addOrder = dao.insertOrder(cusName, address, phone);
                    if (addOrder) {
                        int orderID = dao.getOrderID(cusName, address, phone);
                        Collection<Item> items = cart.getValues();
                        for (Item item : items) {
                            dao.insertOrderDetail(orderID, item.getTitle(), item.getQuantity());
                        }
                    }
                }
                url = LOAD_BOOK_SERVLET;
            }

        } catch (NamingException e) {
            log("CheckoutServlet _ Naming " + e.getMessage());
        } catch (SQLException e) {
            log("CheckoutServlet _ SQL " + e.getMessage());
        } finally {
//            response.sendRedirect(url);
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
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
