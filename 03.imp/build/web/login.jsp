<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inicio de Sesión</h1>
        <form action="LoginController" method="post">
            <input type="hidden" name="f" value="1" />
            <p>Nombre de usuario: <input type="text" name="username" /></p>
            <p>Contraseña: <input type="password" name="password" /></p>
            <p><input type="submit"/></p>
        </form>
    </body>
</html>
