<%-- 
    Document   : error
    Created on : 22-09-2016, 03:35:00 PM
    Author     : ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/estilo.css"/>
    </head>
    <body>
        <h1> Mensaje: ${requestScope['error']} </h1>
    </body>
</html>
