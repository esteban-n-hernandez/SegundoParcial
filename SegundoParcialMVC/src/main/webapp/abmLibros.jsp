<%@ page import="java.util.List" %>
<%@ page import="edu.usal.biblioteca.dominio.Libro" %>
<%@ page import="edu.usal.biblioteca.dominio.Autor" %>
<%@ page import="edu.usal.biblioteca.dominio.Editorial" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ABM Libros</title>
    <script src="js/funciones.js"></script>
</head>
<body>
<h1>ABM Libros</h1>

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
    </tr>
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
    </tr>
    <% } %>
</table>

<button onclick="openForm('agregar')">Agregar Libro</button>

<div id="agregarForm" style="display: none;">
    <form action="abmLibros" method="post">
        <input type="hidden" name="action" value="agregar">
        <input type="text" name="titulo" placeholder="Título">
        <input type="text" name="isbn" placeholder="ISBN">
        <input type="number" name="cantidadDePaginas" placeholder="Cantidad de Páginas">
        <input type="text" name="genero" placeholder="Género">
        <input type="number" name="edicion" placeholder="Edición">

        <!-- Selector de autor -->
        <select name="autorID">
            <% List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores"); %>
            <% for (Autor autor : listaAutores) { %>
            <option value="<%= autor.getId() %>"><%= autor.getNombre() + " " + autor.getApellido() %>
            </option>
            <% } %>
        </select>

        <select name="editorialID">
            <% List<Editorial> listaEditoriales = (List<Editorial>) request.getAttribute("listaEditoriales"); %>
            <% for (Editorial editorial : listaEditoriales) { %>
            <option value="<%= editorial.getId() %>"><%= editorial.getNombreEditorial() %>
            </option>
            <% } %>
        </select>


        <input type="submit" value="Agregar Libro">
    </form>

    <button onclick="closeForm('agregar')">Cerrar</button>
</div>

<button onclick="openForm('editar')">Editar Libro</button>

<div id="editarForm" style="display: none;">

    <form action="abmLibros" method="post">
        <input type="hidden" name="action" value="editar">
        <input type="text" name="isbn" placeholder="ISBn">
        <input type="text" name="titulo" placeholder="Titulo">
        <input type="text" name="cantidadDePaginas" placeholder="CantidadDePaginas">
        <input type="text" name="genero" placeholder="Genero">
        <input type="text" name="edicion" placeholder="Edicion">
        <input type="text" name="autorID" placeholder="AutorID">
        <input type="submit" value="Editar Libro">
    </form>

    <button onclick="closeForm('editar')">Cerrar</button>
</div>

<button onclick="openForm('eliminar')">Eliminar Libro</button>

<div id="eliminarForm" style="display: none;">
    <!-- Formulario para eliminar un libro -->
    <form action="abmLibros" method="post">
        <input type="hidden" name="action" value="eliminar">
        <!-- Solo necesitamos el ISBN para identificar el libro -->
        <input type="text" name="isbn" placeholder="ISBN">
        <input type="submit" value="Eliminar Libro">
    </form>

    <button onclick="closeForm('eliminar')">Cerrar</button>
</div>

</body>
</html>
