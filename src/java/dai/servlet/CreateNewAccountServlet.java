/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.servlet;

import dai.dto.AccountCreateError;
import dai.dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AD
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String INSERT_ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        String url = INSERT_ERROR_PAGE;
        AccountCreateError errors = new AccountCreateError();
        try {
            boolean findErr = false;
            
            if(username.trim().length() < 6 || username.trim().length() > 20){
                findErr = true;
                errors.setUsernameLengthErr("6 <= length of username <= 20");
            }
            
            if(password.trim().length() < 6 || password.trim().length() > 30) {
                findErr = true;
                errors.setPasswordLengthErr("6 <= length of password <= 30");
            } else if(!confirm.trim().equals(password.trim())) {
                findErr = true;
                errors.setConfirmNotMatch("Confirm must match password");
            }
            
            if(fullName.trim().length() < 2 || fullName.trim().length() > 10) {
                findErr = true;
                errors.setFullnameLengthErr("2 <= length of fullname <= 10");
            }
            
            if(findErr) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                AccountDAO dao = new AccountDAO();
                dao.createNewAccount(username, password, fullName, false);
                url = LOGIN_PAGE;
            }
            
        } catch (NamingException e) {
            log("CreateNewAccountServlet _ Naming " + e.getMessage());
        } catch (SQLException e) {
            String msg = e.getMessage();
            log("CreateNewAccountServlet _ SQL " + msg);
            if(msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed!");
            }
            request.setAttribute("CREATE_ERROR", errors);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
