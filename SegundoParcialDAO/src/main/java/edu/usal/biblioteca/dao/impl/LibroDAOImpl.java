package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.dominio.Editorial;
import edu.usal.biblioteca.dominio.Libro;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Libro> getAllLibros() {
        List<Libro> listaLibros = new ArrayList<>();

        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM libros a JOIN autores b ON a.AutorID = b.ID JOIN editoriales c ON a.EditorialID = c.ID;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Libro libro = new Libro();
                Autor autor = new Autor();
                Editorial editorial = new Editorial();

                autor.setId(resultSet.getString("b.id"));
                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setGenero(resultSet.getString("apellido"));
                libro.setEdicion(resultSet.getString("edicion"));
                libro.setCantidadDePaginas(resultSet.getInt("cantidaddepaginas"));
                libro.setAutor(autor);
                editorial.setNombreEditorial(resultSet.getString("nombreEditorial"));
                editorial.setDireccion(resultSet.getString("direccion"));
                editorial.setPaginaWeb(resultSet.getString("paginaWeb"));
                editorial.setNumeroDeContacto(resultSet.getString("numeroDeContacto"));
                editorial.setEmail(resultSet.getString("email"));
                libro.setEditorial(editorial);

                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listaLibros;
    }

    @Override
    public List<Libro> getAllLibrosPorISBNorTitulo(String query) throws SQLException {
        List<Libro> listaLibros = new ArrayList<>();

        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT DISTINCT * FROM libros  JOIN autores ON libros.AutorID = autores.ID JOIN editoriales ON libros.EditorialID = editoriales.ID WHERE Titulo LIKE ? OR ISBN LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, query);
            preparedStatement.setString(2, query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Libro libro = new Libro();
                Autor autor = new Autor();
                Editorial editorial = new Editorial();

                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setGenero(resultSet.getString("apellido"));
                libro.setEdicion(resultSet.getString("edicion"));
                libro.setCantidadDePaginas(resultSet.getInt("cantidaddepaginas"));

                editorial.setNombreEditorial(resultSet.getString("nombreEditorial"));
                editorial.setDireccion(resultSet.getString("direccion"));
                editorial.setPaginaWeb(resultSet.getString("paginaWeb"));
                editorial.setNumeroDeContacto(resultSet.getString("numeroDeContacto"));
                editorial.setEmail(resultSet.getString("email"));
                libro.setEditorial(editorial);

                libro.setAutor(autor);

                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listaLibros;
    }

    @Override
    public void insertLibro(Libro libro) {
        connection = sqlConnection.getConnection();
        try {
            CallableStatement stmt = connection.prepareCall("CALL InsertarLibroYejemplares(?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, libro.getIsbn());
            stmt.setString(2, libro.getTitulo());
            stmt.setInt(3, libro.getCantidadDePaginas());
            stmt.setString(4, libro.getGenero());
            stmt.setString(5, libro.getEdicion());
            stmt.setInt(6, Integer.parseInt(libro.getAutor().getId()));
            stmt.setInt(7, libro.getEditorial().getId());
            stmt.setInt(8, 1);
            stmt.execute();

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Libro getLibro(String isbn) {
        Libro libro = new Libro();
        Autor autor = new Autor();
        try {
            connection = sqlConnection.getConnection();
            String sql = "select * FROM libros, autores";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setGenero(resultSet.getString("apellido"));
                libro.setEdicion(resultSet.getString("edicion"));
                libro.setCantidadDePaginas(resultSet.getInt("cantidaddepaginas"));
                libro.setAutor(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return libro;
    }

    @Override
    public void updateLibro(Libro libro) {

    }

    @Override
    public void deleteLibro(String isbn) throws SQLException {
        try {
            connection = sqlConnection.getConnection();

            String sql = "DELETE FROM Libros WHERE ISBN = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Libro> getAllLibrosDisponibles() {
        List<Libro> listaLibros = new ArrayList<>();

        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM libros a JOIN autores b ON a.AutorID = b.ID JOIN editoriales c ON a.EditorialID = c.ID JOIN Ejemplares d ON a.ISBN = d.LibroISBN WHERE d.Estado = 'disponible'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Libro libro = new Libro();
                Autor autor = new Autor();
                Editorial editorial = new Editorial();

                autor.setId(resultSet.getString("b.id"));
                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setGenero(resultSet.getString("apellido"));
                libro.setEdicion(resultSet.getString("edicion"));
                libro.setCantidadDePaginas(resultSet.getInt("cantidaddepaginas"));
                libro.setAutor(autor);
                editorial.setNombreEditorial(resultSet.getString("nombreEditorial"));
                editorial.setDireccion(resultSet.getString("direccion"));
                editorial.setPaginaWeb(resultSet.getString("paginaWeb"));
                editorial.setNumeroDeContacto(resultSet.getString("numeroDeContacto"));
                editorial.setEmail(resultSet.getString("email"));
                libro.setEditorial(editorial);

                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listaLibros;
    }

    private void closeConnection() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
}