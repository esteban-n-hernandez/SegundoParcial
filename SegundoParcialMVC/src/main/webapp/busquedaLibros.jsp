<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Libro" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Libros</title>
</head>
<body>
<h1>Buscar Libros</h1>
<!-- Lista de Libros -->
<% List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros"); %>
<table border="1">
    <tr>
        <th>Título</th>
        <th>ISBN</th>
        <th>Cantidad de Páginas</th>
        <th>Género</th>
        <th>Edición</th>
        <th>Autor</th>
        <th>Editorial</th>
        <th>Reservar</th>
    </tr>
    <% if (listaLibros != null && !listaLibros.isEmpty()) { %>
    <% for (Libro libro : listaLibros) { %>
    <tr>
        <td><%= libro.getTitulo() %>
        </td>
        <td><%= libro.getIsbn() %>
        </td>
        <td><%= libro.getCantidadDePaginas() %>
        </td>
        <td><%= libro.getGenero() %>
        </td>
        <td><%= libro.getEdicion() %>
        </td>
        <td><%= libro.getAutor().getNombre() + " " + libro.getAutor().getApellido() %>
        </td>
        <td><%= libro.getEditorial().getNombreEditorial() %>
        </td>

        <!-- Formulario de reserva -->
        <td>
            <form action="busquedaLibros" method="post">
                <input type="hidden" name="action" value="reservar">
                <input type="text" name="clienteDNI" placeholder="DNI del Cliente">
                <input type="hidden" name="LibroISBN" value="<%= libro.getIsbn() %>">
                <input type="submit" value="Reservar">
            </form>
        </td>

    </tr>
    <% } %>

    <% } else { %>

    <!-- Si no se encontraron libros -->
    <tr>
        <td colspan="9">No se encontraron libros con los criterios de búsqueda.</td>
    </tr>

    <% } %>

</table>

<!-- Formulario de búsqueda -->
<form action="busquedaLibros" method="get">
    <input type="text" id="query" name="query" placeholder="Título o ISBN del libro">
    <!-- Filtro por editorial -->
    <select id="editorial" name="editorial">
    </select>
    <input type="submit" value="Buscar">
</form>


</body>
</html>



