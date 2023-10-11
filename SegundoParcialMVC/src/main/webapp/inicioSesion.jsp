<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Inicio de Sesión</title>
</head>
<body>
<h1>Inicio de Sesión</h1>

<!-- Formulario para iniciar sesión -->
<form action="iniciarSesion" method="post">
  <input type="text" name="username" placeholder="Nombre de usuario">
  <input type="password" name="password" placeholder="Contraseña">
  <input type="submit" value="Iniciar Sesión">
</form>

<!-- Enlace para cerrar la sesión -->
<a href="cerrarSesion">Cerrar Sesión</a>
</body>
</html>
