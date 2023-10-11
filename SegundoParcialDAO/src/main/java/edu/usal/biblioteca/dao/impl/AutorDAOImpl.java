package edu.usal.biblioteca.dao.impl;


import edu.usal.biblioteca.dao.interfaz.AutorDAO;
import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {
    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Autor> getAllAutores() {
        List<Autor> listaAdministrativos = new ArrayList<>();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Autores";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Autor autor = new Autor();
                autor.setId(resultSet.getString("id"));
                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                listaAdministrativos.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listaAdministrativos;
    }

    @Override
    public Autor getAutor(String id) {
        Autor autor = new Autor();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Autores where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                autor.setNombre(resultSet.getString("id"));
                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return autor;
    }

    @Override
    public void insertAutor(Autor autor) {
        connection = sqlConnection.getConnection();
        try {
            CallableStatement stmt = connection.prepareCall("CALL InsertarEnAutores(?, ?, ?)");

            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellido());
            stmt.setString(3, autor.getNacionalidad());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateAutor(Autor libro) {

    }

    @Override
    public void deleteAutor(Integer id) throws SQLException {
        try {
            connection = sqlConnection.getConnection();

            CallableStatement stmt = connection.prepareCall("CALL EliminarAutor(?)");
            stmt.setInt(1, id);
            stmt.execute();
        } finally {
            closeConnection();
        }
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