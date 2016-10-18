<%-- 
    Document   : nuevo-libro
    Created on : 27-09-2016, 07:57:25 PM
    Author     : admin
--%>

<%@page import="ucb.taller2.biblioteca.model.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Libro</title>
    </head>
    <body>
        <h1>Nuevo Libro</h1>
        <%
            Libro libro = (Libro)request.getAttribute("libro");
        %>
        <div class="form">
            <jsp:include page="libro-form.jsp">
                <jsp:param name="accion" value="ins" />
                <jsp:param name="codigo" value="<%=libro.getCodigo()%>" />
                <jsp:param name="titulo" value="<%=libro.getTitulo()%>" />
            </jsp:include>            
        </div>       
    </body>
</html>
