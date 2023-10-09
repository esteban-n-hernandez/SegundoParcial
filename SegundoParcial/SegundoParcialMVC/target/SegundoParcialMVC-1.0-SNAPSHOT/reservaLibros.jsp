<%@ page import="edu.usal.biblioteca.dominio.Libro" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reserva de Libros</title>
</head>
<body>
<h1>Reserva de Libros</h1>

<!-- Formulario para buscar un libro -->
<form action="buscarLibro" method="get">
    <input type="text" name="tituloOIsbn" placeholder="Título o ISBN">
    <input type="submit" value="Buscar Libro">
</form>

<!-- Lista de resultados de búsqueda -->
<% List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros"); %>
<% for (Libro libro : listaLibros) { %>
<p><%= libro.getTitulo() %></p>
<!-- Enlace para reservar el libro -->
<a href="reservarLibro?isbn=<%= libro.getIsbn() %>">Reservar</a>
<% } %>
</body>
</html>
