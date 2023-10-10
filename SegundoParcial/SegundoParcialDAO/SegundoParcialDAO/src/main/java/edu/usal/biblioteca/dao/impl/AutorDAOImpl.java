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
            connection = sqlConnection.getConnectiion();
            String sql = "SELECT * FROM Autores";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Autor autor = new Autor();
                autor.setNombre(resultSet.getString("nombre"));
                autor.setApellido(resultSet.getString("apellido"));
                autor.setNacionalidad(resultSet.getString("nacionalidad"));
                listaAdministrativos.add(autor);
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
        return listaAdministrativos;
    }

    @Override
    public Autor getAutor(String isbn) {
        return null;
    }

    @Override
    public void insertAutor(Autor autor) {
        connection = sqlConnection.getConnectiion();
        try {
            CallableStatement stmt = connection.prepareCall("CALL InsertarEnAutores(?, ?, ?)");

            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellido());
            stmt.setString(3, autor.getNacionalidad());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAutor(Autor libro) {

    }

    @Override
    public void deleteAutor(Integer id) throws SQLException {
        connection = sqlConnection.getConnectiion();
        CallableStatement stmt = connection.prepareCall("CALL EliminarAutor(?)");
        stmt.setInt(1, id);
        stmt.execute();
    }

}