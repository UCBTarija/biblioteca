<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="LoginController" method="post">
            <p>Nombre de usuario: <input type="text" name="username" /></p>
            <p>Contraseña: <input type="text" name="password" /></p>
            <p><input type="submit"/></p>
        </form>
    </body>
</html>
