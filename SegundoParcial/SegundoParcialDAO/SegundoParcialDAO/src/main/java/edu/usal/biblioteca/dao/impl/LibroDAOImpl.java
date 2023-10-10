package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.dominio.Libro;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public List<Libro> getAllLibros() {
        List<Libro> listaLibros = new ArrayList<>();

        Connection connection = null;
        SQLConnection sqlConnection = new SQLConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = sqlConnection.getConnectiion();
            String sql = "select * FROM libros, autores";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Libro libro = new Libro();
                Autor autor = new Autor();

                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setIsbn(resultSet.getString("isbn"));
                libro.setGenero(resultSet.getString("apellido"));
                libro.setEdicion(resultSet.getString("edicion"));
                libro.setCantidadDePaginas(resultSet.getInt("cantidaddepaginas"));
                libro.setAutor(autor);

                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return listaLibros;
    }

    @Override
    public void insertLibro(Libro libro) {

    }

    @Override
    public Libro getLibro(String isbn) {
        Connection connection = null;
        SQLConnection sqlConnection = new SQLConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Libro libro = new Libro();
        Autor autor = new Autor();
        try {
            connection = sqlConnection.getConnectiion();
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
        return libro;
    }

    @Override
    public void updateLibro(Libro libro) {

    }

    @Override
    public void deleteLibro(String isbn) {

    }
}