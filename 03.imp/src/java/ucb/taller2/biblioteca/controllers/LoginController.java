package ucb.taller2.biblioteca.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucb.taller2.biblioteca.model.Authenticator;
import ucb.taller2.biblioteca.model.User;

/**
 *
 * @author ronal
 */
public class LoginController extends HttpServlet {

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
        
        /* Obtiene el usuario y clave enviado por el formulario*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        /* Inicializa el autenticador y valida el usuario y clave*/ 
        Authenticator authenticator = new Authenticator();        
        String result = authenticator.authenticate(username, password);
        
        if(result.equals("success")){
            /*Si el usuario es válido, inicializa una instancia de
            usuario y la almacena en la sesión*/
            User user = new User(username, password);
            request.getSession().setAttribute("user", user);
            
            /*se redirige al controlador de libros*/
            response.sendRedirect("LibroController");
        } else {
            request.setAttribute("error", "Fallo al iniciar sesión intente de nuevo");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
