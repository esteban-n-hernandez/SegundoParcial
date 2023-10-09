<%@ page import="edu.usal.biblioteca.dominio.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ABM Clientes</title>
    <script src="funciones.js"></script>
</head>
<body>
<h1>ABM Clientes</h1>

<!-- Lista de clientes -->
<% List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes"); %>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>DNI</th>
        <th>Teléfono</th>
        <th>Correo Electrónico</th>
    </tr>
    <% for (Cliente cliente : listaClientes) { %>
    <tr>
        <td><%= cliente.getNombre() %>
        </td>
        <td><%= cliente.getApellido() %>
        </td>
        <td><%= cliente.getDni() %>
        </td>
        <td><%= cliente.getTelefono() %>
        </td>
        <td><%= cliente.getEmail() %>
        </td>
    </tr>
    <% } %>
</table>

<button onclick="openForm('agregar')">Agregar Cliente</button>

<div id="agregarForm" style="display: none;">
    <!-- Formulario para agregar un cliente -->
    <form action="abmClientes" method="post">
        <input type="hidden" name="action" value="agregar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="dni" placeholder="DNI">
        <input type="text" name="telefono" placeholder="Teléfono">
        <input type="text" name="email" placeholder="Correo Electrónico">
        <input type="submit" value="Agregar Cliente">
    </form>

    <button onclick="closeForm('agregar')">Cerrar</button>
</div>

<button onclick="openForm('editar')">Editar Cliente</button>

<div id="editarForm" style="display: none;">
    <!-- Formulario para editar un cliente -->
    <form action="abmClientes" method="post">
        <input type="hidden" name="action" value="editar">
        <input type="text" name="nombre" placeholder="Nombre">
        <input type="text" name="apellido" placeholder="Apellido">
        <input type="text" name="dni" placeholder="DNI">
        <input type="text" name="telefono" placeholder="Teléfono">
        <input type="text" name="email" placeholder="Correo Electrónico">
        <input type="submit" value="Editar Cliente">
    </form>

    <button onclick="closeForm('editar')">Cerrar</button>
</div>

<button onclick="openForm('eliminar')">Eliminar Cliente</button>

<div id="eliminarForm" style="display: none;">
    <!-- Formulario para eliminar un cliente -->
    <form action="abmClientes" method="post">
        <input type="hidden" name="action" value="eliminar">
        <input type="text" name="dni" placeholder="DNI">
        <input type="submit" value="Eliminar Cliente">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>
