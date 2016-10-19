<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="LibroController" method="post">
    <table>
        <input type="hidden" name="f" value="1" />
        <input type="hidden" name="accion" value="${param.accion}" />
        <tr>
            <td>Codigo</td>
            <td><input type="text" name="codigo" value="${param.codigo}" /></td>
        </tr>    
        <tr>
            <td>Titulo</td>
            <td><input type="text" name="titulo" value="${param.titulo}"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Guardar" /></td>
        </tr>
    </table>
</form>