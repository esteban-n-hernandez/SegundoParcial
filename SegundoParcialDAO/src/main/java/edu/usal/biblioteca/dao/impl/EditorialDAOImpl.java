package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.EditorialDAO;
import edu.usal.biblioteca.dominio.Editorial;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAOImpl implements EditorialDAO {
    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Editorial> getAllEditoriales() {
        List<Editorial> listaEditoriales = new ArrayList<>();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Editoriales";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Editorial editorial = new Editorial();
                editorial.setId(resultSet.getInt("id"));
                editorial.setNombreEditorial(resultSet.getString("nombreEditorial"));
                editorial.setDireccion(resultSet.getString("direccion"));
                editorial.setNumeroDeContacto(resultSet.getString("numeroDeContacto"));
                editorial.setPaginaWeb(resultSet.getString("paginaWeb"));
                editorial.setEmail(resultSet.getString("email"));
                listaEditoriales.add(editorial);
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
        return listaEditoriales;
    }

    @Override
    public Editorial getEditorial(String nombreEditorial) {
        Editorial editorial = new Editorial();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Editoriales WHERE NombreEditorial = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nombreEditorial);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                editorial.setNombreEditorial(resultSet.getString("nombreEditorial"));
                editorial.setDireccion(resultSet.getString("direccion"));
                editorial.setNumeroDeContacto(resultSet.getString("numeroDeContacto"));
                editorial.setPaginaWeb(resultSet.getString("paginaWeb"));
                editorial.setEmail(resultSet.getString("email"));
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
        return editorial;
    }

    @Override
    public void insertEditorial(Editorial editorial) {
        connection = sqlConnection.getConnection();
        try {
            CallableStatement stmt = connection.prepareCall("CALL InsertarEnEditoriales(?, ?, ?, ?, ?)");

            stmt.setString(1, editorial.getNombreEditorial());
            stmt.setString(2, editorial.getDireccion());
            stmt.setString(3, editorial.getPaginaWeb());
            stmt.setString(4, editorial.getEmail());
            stmt.setString(5, editorial.getNumeroDeContacto());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEditorial(Editorial editorial) throws SQLException {
        connection = sqlConnection.getConnection();

        String sql = "UPDATE Editoriales SET NombreEditorial = ? , Direccion = ? , PaginaWeb = ? , Email = ?, NumeroDeContacto = ? WHERE NombreEditorial = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, editorial.getNombreEditorial());
        preparedStatement.setString(2, editorial.getDireccion());
        preparedStatement.setString(3, editorial.getPaginaWeb());
        preparedStatement.setString(4, editorial.getEmail());
        preparedStatement.setString(5, editorial.getNumeroDeContacto());
        preparedStatement.setString(6, editorial.getNombreEditorial());

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("Filas afectadas: " + rowsAffected);
    }

    @Override
    public void deleteEditorial(String nombreEditorial) throws SQLException {
        connection = sqlConnection.getConnection();

        String sql = "DELETE FROM Editoriales WHERE NombreEditorial = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nombreEditorial);

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("Filas afectadas: " + rowsAffected);
    }
}