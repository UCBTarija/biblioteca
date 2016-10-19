package ucb.taller2.biblioteca.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucb.taller2.biblioteca.model.Libro;

/**
 *
 * @author admin
 */
@WebServlet(name = "LibroController", urlPatterns = {"/LibroController"})
public class LibroController extends HttpServlet {

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
        //si no se especificó la acción entonces es INDEX
        if (accion == null) {
            accion = "idx";
        }

        switch (accion) {
            case "idx":
                this.actionIndex(request, response);
            case "ins":
                this.actionNuevo(request, response);
                break;
            case "del":
                this.actionEliminar(request, response);
                break;
            case "upd":
                this.actionModificar(request, response);
                break;
        }
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            /* Carga la lista de libros en la variable "libros" del objeto 
            request para que pase a la siguiente vista*/
            request.setAttribute("libros", Libro.getLibros());
            /* carga la siguiente vista */
            request.getRequestDispatcher("/libro/index.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void actionNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            /*el parámetro f indica que es el retorno de un formulario*/
            if (request.getParameter("f") != null) {
                /* Se inicializa un objeto libro con los parámetros recibidos
                del formulario*/
                Libro libro = new Libro();
                libro.setCodigo(request.getParameter("codigo"));
                libro.setTitulo(request.getParameter("titulo"));
                
                /* Guarda el libro en la BD*/
                libro.guardar();

                /*para una respuesta post se hará una redirección a la siguiente ventana (get)*/
                response.sendRedirect("LibroController");
            } else {
                /*si no existe el parámetro f significa que es la primera vez que se ejeucta
                por lo tanto hay que mostrar el formulario*/
                request.setAttribute("libro", new Libro());
                request.getRequestDispatcher("/libro/nuevo-libro.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            /*en caso de existir un error se carga la vista error.jsp*/
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void actionModificar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String codigo = request.getParameter("codigo");
            if (codigo == null) {
                throw new Exception("Debe proporcionar el código del libro");
            }
            
            /*carga el libro que se desea modificar*/
            Libro libro = Libro.getById(codigo);            
            
            /*el parámetro f indica que es el retorno de un formulario*/
            if (request.getParameter("f") != null) {
                /* Se modifica el libro con los parámetros recibidos
                del formulario*/
                libro.setCodigo(request.getParameter("codigo"));
                libro.setTitulo(request.getParameter("titulo"));
                
                /* Guarda el libro en la BD*/
                libro.modificar();

                /*para una respuesta post se hará una redirección a la siguiente ventana (get)*/
                response.sendRedirect("LibroController");                
            } else {
                /*si no existe el parámetro f significa que es la primera vez que se ejeucta
                por lo tanto hay que mostrar el formulario con los datos del libro*/
                request.setAttribute("libro", libro);
                System.out.println("Pasando a vista..." + codigo);
                request.getRequestDispatcher("/libro/modificar-libro.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            /*en caso de existir un error se carga la vista error.jsp*/
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void actionEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String codigo = request.getParameter("codigo");
            if (codigo == null) {
                throw new Exception("Debe proporcionar el código del libro");
            }
            /*carga el libro que se desea eliminar*/
            Libro libro = Libro.getById(codigo);

            if (libro != null) {
                libro.eliminar();
            } 
            /*ante cada acción sobre una bd se realiza una redirección*/
            response.sendRedirect("LibroController");
        } catch (Exception ex) {
            request.setAttribute("error", ex.toString());
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
