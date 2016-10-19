package ucb.taller2.biblioteca.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucb.taller2.biblioteca.model.Authenticator;
import ucb.taller2.biblioteca.model.User;

/**
 *
 * @author ronal
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
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

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "login";
        }

        System.out.println("squi");

        switch (accion) {
            case "login":
                this.actionLogin(request, response);
                break;
            case "menu":
                this.actionMenu(request, response);
                break;
            case "logout": {
                this.actionLogout(request, response);
                break;
            }
        }
    }

    private void actionMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/menu.jsp").forward(request, response);
    }

    private void actionLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            if (request.getParameter("f") != null) {
                /* Obtiene el usuario y clave enviado por el formulario*/
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                /* Inicializa el autenticador y valida el usuario y clave*/
                Authenticator authenticator = new Authenticator();
                String result = authenticator.authenticate(username, password);

                if (result.equals("success")) {
                    /*Si el usuario es válido, inicializa una instancia de
                  usuario y la almacena en la sesión*/
                    User user = new User(username, password);
                    request.getSession().setAttribute("user", user);

                    /*se redirige al controlador de libros*/
                    response.sendRedirect("LoginController?accion=menu");
                } else {
                    //lanza el error para que lo capture el catch
                    throw new Exception("Fallo al realizar la autenticación");
                }
            } else {
                /*si no existe el parámetro f significa que es la primera vez que se ejeucta
                por lo tanto hay que mostrar el formulario*/
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            /*en caso de existir un error se carga la vista error.jsp*/
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void actionLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Saliendo...");
        request.getSession().removeAttribute("user");
        response.sendRedirect("index.html");
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
