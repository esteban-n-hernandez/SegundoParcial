<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Autor" %>
<%@ page import="edu.usal.biblioteca.dominio.Editorial" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ABM Editoriales</title>
    <script src="js/funciones.js"></script>
</head>
<body>
<h1>ABM Editoriales</h1>


<!-- Mensaje de error -->
<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %>
</p>
<% } %>

<!-- Lista de Autores -->
<% List<Editorial> listaEditoriales = (List<Editorial>) request.getAttribute("listaEditoriales"); %>
<table border="1">
    <tr>
        <!--    <th>ID</th> -->
        <th>NombreEditorial</th>
        <th>Direccion</th>
        <th>PaginaWeb</th>
        <th>Email</th>
        <th>NumeroDeContacto</th>
    </tr>
    <% for (Editorial editorial : listaEditoriales) { %>
    <tr>
        <td><%= editorial.getNombreEditorial() %>
        </td>
        <td><%= editorial.getDireccion() %>
        </td>
        <td><%= editorial.getPaginaWeb() %>
        </td>
        <td><%= editorial.getEmail() %>
        </td>
        <td><%= editorial.getNumeroDeContacto() %>
        </td>
    </tr>
    <% } %>
</table>

<button onclick="openForm('agregar')">Agregar Editorial</button>

<div id="agregarForm" style="display: none;">
    <form action="abmEditoriales" method="post">
        <input type="hidden" name="action" value="agregar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="direccion" placeholder="Direccion">
        <input type="text" name="email" placeholder="Email">
        <input type="text" name="paginaweb" placeholder="Pagina Web">
        <input type="text" name="numerocontacto" placeholder="Numero De Contacto">
        <input type="submit" value="Agregar Editorial">
    </form>
    <button onclick="closeForm('agregar')">Cerrar</button>
</div>

<button onclick="openForm('editar')">Editar Editorial</button>

<div id="editarForm" style="display: none;">
    <form action="abmEditoriales" method="post">
        <input type="hidden" name="action" value="editar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="direccion" placeholder="Direccion">
        <input type="text" name="paginaweb" placeholder="Pagina Web">
        <input type="text" name="numerocontacto" placeholder="Numero de Contacto">
        <input type="submit" value="Editar Editorial">
    </form>

    <button onclick="closeForm('editar')">Cerrar</button>
</div>

<button onclick="openForm('eliminar')">Eliminar Editorial</button>

<div id="eliminarForm" style="display: none;">
    <form action="abmEditoriales" method="post">
        <input type="hidden" name="action" value="eliminar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="submit" value="Eliminar Editorial">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>
