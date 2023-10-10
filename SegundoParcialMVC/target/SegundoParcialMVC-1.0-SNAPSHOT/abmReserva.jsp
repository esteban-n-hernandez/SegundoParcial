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

<!-- Lista de Reservas -->
<% List<Reserva> listaReservas = (List<Reserva>) request.getAttribute("listaReservas"); %>
<table border="1">
    <tr>
        <th>Libro ISBN</th>
        <th>Cliente DNI</th>
        <th>Fecha Reserva</th>
        <th>Fecha Devolucion</th>
    </tr>
    <% for (Reserva reserva : listaReservas) { %>
    <tr>
        <td><%= reserva.getLibroISBN() %>
        </td>
        <td><%= reserva.getClienteDNI() %>
        </td>
        <td><%= reserva.getFechaDeReserva() %>
        </td>
        <td><%= reserva.getFechaDeDevolucion() %>
        </td>
    </tr>
    <% } %>
</table>

<button onclick="openForm('agregar')">Agregar Reserva</button>

<div id="agregarForm" style="display: none;">
    <!-- Formulario para agregar una reserva -->
    <form action="abmReserva" method="post">
        <input type="hidden" name="action" value="agregar">
        <input type="text" name="clienteDNI" placeholder="DNI del Cliente">
        <input type="text" name="libroISBN" placeholder="ISBN del Libro">
        <input type="date" name="fechaReserva" placeholder="Fecha de Reserva">
        <input type="date" name="fechaDevolucion" placeholder="Fecha de Devolución">
        <input type="submit" value="Agregar Reserva">
    </form>

    <button onclick="closeForm('agregar')">Cerrar</button>
</div>

<button onclick="openForm('editar')">Editar Reserva</button>

<div id="editarForm" style="display: none;">
    <!-- Formulario para editar una reserva -->
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
    <!-- Formulario para eliminar una reserva -->
    <form action="ReservaServlet" method="post">
        <input type="hidden" name="action" value="eliminar">
        <!-- Solo necesitamos el ID para identificar la reserva -->
        <input type="text" name="idReserva" placeholder="ID de la Reserva">
        <input type="submit" value="Eliminar Reserva">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>