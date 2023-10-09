<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Administrativo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ABM Administrativos</title>
    <script>
        function openForm(action) {
            document.getElementById(action + 'Form').style.display = 'block';
        }

        function closeForm(action) {
            document.getElementById(action + 'Form').style.display = 'none';
        }
    </script>
</head>
<body>
<h1>ABM Administrativos</h1>

<!-- Lista de Administrativos -->
<% List<Administrativo> listaAdministrativos = (List<Administrativo>) request.getAttribute("listaAdministrativos"); %>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>DNI</th>
        <th>Teléfono</th>
        <th>Legajo</th>
    </tr>
    <% for (Administrativo administrativo : listaAdministrativos) { %>
    <tr>
        <td><%= administrativo.getNombre() %>
        </td>
        <td><%= administrativo.getApellido() %>
        </td>
        <td><%= administrativo.getDni() %>
        </td>
        <td><%= administrativo.getTelefono() %>
        </td>
        <td><%= administrativo.getLegajo() %>
        </td>
    </tr>
    <% } %>
</table>

<button onclick="openForm('agregar')">Agregar Administrativo</button>

<div id="agregarForm" style="display: none;">
    <!-- Formulario para agregar un administrativo -->
    <form action="abmAdministrativos" method="post">
        <input type="hidden" name="action" value="agregar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="dni" placeholder="DNI">
        <input type="text" name="telefono" placeholder="Teléfono">
        <input type="text" name="legajo" placeholder="Legajo">
        <input type="submit" value="Agregar Administrativo">
    </form>

    <button onclick="closeForm('agregar')">Cerrar</button>
</div>

<button onclick="openForm('editar')">Editar Administrativo</button>

<div id="editarForm" style="display: none;">
    <!-- Formulario para editar un administrativo -->
    <form action="abmAdministrativos" method="post">
        <input type="hidden" name="action" value="editar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="dni" placeholder="DNI">
        <input type="text" name="telefono" placeholder="Teléfono">
        <input type="text" name="legajo" placeholder="Legajo">
        <input type="submit" value="Editar Administrativo">
    </form>

    <button onclick="closeForm('editar')">Cerrar</button>
</div>

<button onclick="openForm('eliminar')">Eliminar Administrativo</button>

<div id="eliminarForm" style="display: none;">
    <!-- Formulario para eliminar un Administrativo -->
    <form action="abmAdministrativos" method="post">
        <input type="hidden" name="action" value="eliminar">
        <input type="text" name="dni" placeholder="DNI">
        <input type="submit" value="Eliminar Administrativo">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>
