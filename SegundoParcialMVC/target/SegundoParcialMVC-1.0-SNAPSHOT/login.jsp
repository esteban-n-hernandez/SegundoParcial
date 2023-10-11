<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar sesión</title>
</head>
<body>
<h2>Iniciar sesión</h2>
<form action="login" method="post">
    <label for="dni">DNI:</label><br>
    <input type="text" id="dni" name="dni"><br>
    <label for="tipoUsuario">Tipo de usuario:</label><br>
    <select id="tipoUsuario" name="tipoUsuario">
        <option value="cliente">Cliente</option>
        <option value="administrativo">Administrativo</option>
    </select><br>
    <input type="submit" value="Iniciar sesión">
</form>

</body>
</html>
