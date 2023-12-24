package oscar.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oscar.users.UserDAO;
import oscar.users.UserDTO;
import oscar.utils.Constants;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

    private static final String ERROR = Constants.ERROR_PAGE;
    private static final String SUCCESS = Constants.USER_MGMT_PAGE;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            UserDAO userDAO = new UserDAO();
            
            UserDTO loginUser = userDAO.authentication(email, password);
            if (loginUser != null) {
                if(Constants.ADMIN_ROLE.equals(loginUser.getRole())){
                    session.setAttribute("LOGIN USER", loginUser);
                    url = SUCCESS;
                }
            }
            
        } catch (Exception ex) {
            log("Error at AuthController: " +ex.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
