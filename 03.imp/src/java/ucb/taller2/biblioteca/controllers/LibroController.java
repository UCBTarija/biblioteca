/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

        if (accion == null) {
            this.actionIndex(request, response);
        }

        switch (accion) {
            case "ins":
                this.actionNuevo(request, response);
                break;
            case "del":
                this.actionEliminar(request, response);
                break;
        }
    }

    private void actionIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("libros", Libro.getLibros());
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", ex.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void actionNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            if (request.getParameter("f") != null) {
                Libro libro = new Libro();
                libro.setCodigo(request.getParameter("codigo"));
                libro.setTitulo(request.getParameter("titulo"));
                libro.guardar();

                response.sendRedirect("LibroController");
            } else {
                request.getRequestDispatcher("nuevo-libro.jsp").forward(request, response);
            }
        } catch (Exception ex) {
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

            Libro libro = Libro.getById(codigo);

            if (libro != null) {
                libro.eliminar();
            } 

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
