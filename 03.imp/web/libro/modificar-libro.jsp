<%-- 
    Document   : modificar-libro
    Created on : 18-10-2016, 06:09:22 PM
    Author     : ronal
--%>

<%@page import="ucb.taller2.biblioteca.model.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Libro</title>
    </head>
    <body>
        <h1>Modificar Libro</h1>
        <%
            Libro libro = (Libro)request.getAttribute("libro");
        %>
        <div class="form">
            <jsp:include page="libro-form.jsp">
                <jsp:param name="accion" value="upd" />
                <jsp:param name="codigo" value="<%=libro.getCodigo()%>" />
                <jsp:param name="titulo" value="<%=libro.getTitulo()%>" />
            </jsp:include>            
        </div>       
    </body>
</html>

