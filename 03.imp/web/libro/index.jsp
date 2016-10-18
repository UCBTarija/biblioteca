<%-- 
    Document   : success
    Created on : 22-09-2016, 03:34:40 PM
    Author     : ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="ucb.taller2.biblioteca.model.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>    
    <body>
        <h1>Lista de libros </h1>
        <nav>
            <a href="LibroController?accion=ins">Nuevo Libro</a>
        </nav>
        
        <section>
            <h2>Lista de libros</h2>
            <table border="1">
                <tr>
                    <th>Codigo</th>
                    <th>Titulo</th>
                    <th></th>
                </tr>
                
                <% for (Libro libro : (List<Libro>) request.getAttribute("libros")) { %>
                <tr>
                    <td><%=libro.getCodigo() %></td>
                    <td><%=libro.getTitulo() %></td>
                    <td><a href="LibroController?accion=del&codigo=<%=libro.getCodigo()%>" 
                           onClick="return confirm('Desea eliminar el elemento?')">
                            Eliminar
                        </a>
                    </td>                    
                    <td><a href="LibroController?accion=upd&codigo=<%=libro.getCodigo()%>">
                            Modificar
                        </a>
                    </td>                    
                </tr>           
                <% }%>
            </table>
        </section>
    </body>
</html>
