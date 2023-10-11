<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Reserva" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestionar Reservas</title>
    <script src="js/funciones.js"></script>
</head>
<body>
<h1>Gestionar Reservas</h1>

<% List<Reserva> listaReservas = (List<Reserva>) request.getAttribute("listaReservas"); %>
<table border="1">
    <tr>
        <th>Libro ISBN</th>
        <th>Cliente DNI</th>
        <th>Aceptar Reserva</th>
    </tr>
    <% for (Reserva reserva : listaReservas) { %>
    <tr>
        <td><%= reserva.getLibroISBN() %></td>
        <td><%= reserva.getClienteDNI() %></td>
        <!-- Agregamos un botón en cada fila que dice "Aceptar Reserva" -->
        <td>
            <form action="abmReserva" method="post">
                <input type="hidden" name="action" value="agregar">
                <input type="hidden" name="isbn" value="<%= reserva.getLibroISBN() %>">
                <input type="submit" value="Aceptar Reserva">
            </form>
        </td>
    </tr>
    <% } %>
</table>

<button onclick="openForm('editar')">Editar Reserva</button>

<div id="editarForm" style="display: none;">
    <form action="abmReserva" method="post">
        <input type="hidden" name="action" value="editar">
        <input type="text" name="dni" placeholder="DNI Cliente">
        <input type="text" name="libroISBN" placeholder="LibroISBN">
        <input type="date" name="fechaReserva" placeholder="Fecha de Reserva">
        <input type="date" name="fechaDevolucion" placeholder="Fecha de Devolución">

        <input type="submit" value="Editar Reserva">
    </form>

    <button onclick="closeForm('editar')">Cerrar</button>
</div>

<button onclick="openForm('eliminar')">Eliminar Reserva</button>

<div id="eliminarForm" style="display: none;">
    <form action="ReservaServlet" method="post">
        <input type="hidden" name="action" value="eliminar">
        <input type="text" name="idReserva" placeholder="ID de la Reserva">
        <input type="submit" value="Eliminar Reserva">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>
