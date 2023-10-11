<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Autor" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ABM Autores</title>
    <script src="js/funciones.js"></script>
</head>
<body>
<h1>ABM Autores</h1>


<!-- Mensaje de error -->
<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %>
</p>
<% } %>

<!-- Lista de Autores -->
<% List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores"); %>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Nacionalidad</th>
    </tr>
    <% for (Autor autor : listaAutores) { %>
    <tr>
        <td><%= autor.getNombre() %>
        </td>
        <td><%= autor.getApellido() %>
        </td>
        <td><%= autor.getNacionalidad() %>
        </td>
    </tr>
    <% } %>
</table>

<button onclick="openForm('agregar')">Agregar Autor</button>

<div id="agregarForm" style="display: none;">
    <form action="abmAutores" method="post">
        <input type="hidden" name="action" value="agregar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="nacionalidad" placeholder="Nacionalidad">
        <input type="submit" value="Agregar Autor">
    </form>

    <button onclick="closeForm('agregar')">Cerrar</button>
</div>

<button onclick="openForm('editar')">Editar Autor</button>

<div id="editarForm" style="display: none;">
    <form action="abmAutores" method="post">
        <input type="hidden" name="action" value="editar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="nacionalidad" placeholder="nacionalidad">
        <input type="submit" value="Editar Autor">
    </form>

    <button onclick="closeForm('editar')">Cerrar</button>
</div>

<button onclick="openForm('eliminar')">Eliminar Autor</button>

<div id="eliminarForm" style="display: none;">
    <form action="abmAutores" method="post">
        <input type="hidden" name="action" value="eliminar">
        <input type="text" name="id" placeholder="ID">
        <input type="submit" value="Eliminar Autor">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>
