<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Cliente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>
</head>
<body>
<h1>Listado de Clientes</h1>
<table>
    <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>DNI</th>
        <th>Teléfono</th>
        <th>Email</th>
    </tr>
    <% List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes"); %>
    <% for (Cliente cliente : listaClientes) { %>
    <tr>
        <td><%= cliente.getNombre() %></td>
        <td><%= cliente.getApellido() %></td>
        <td><%= cliente.getDni() %></td>
        <td><%= cliente.getTelefono() %></td>
        <td><%= cliente.getEmail() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
