<%-- 
    Document   : nuevo-libro
    Created on : 27-09-2016, 07:57:25 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Libro</title>
    </head>
    <body>
        <h1>Nuevo Libro</h1>
        
        <table>
        <form action="LibroController" >
            <input type="hidden" name="accion" value="ins" />
            <input type="hidden" name="f" value="1" />
            <tr>
                <td>Codigo</td>
                <td><input type="text" name="codigo" /></td>
            </tr>    
            <tr>
                <td>Titulo</td>
                <td><input type="text" name="titulo" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Guardar" /></td>
            </tr>
        </form>
        </table>
    </body>
</html>
