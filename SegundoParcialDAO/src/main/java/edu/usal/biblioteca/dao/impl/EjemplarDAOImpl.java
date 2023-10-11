package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.EjemplarDAO;
import edu.usal.biblioteca.dominio.Ejemplar;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EjemplarDAOImpl implements EjemplarDAO {

    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public List<Ejemplar> getAllEjemplares() {
        return null;
    }

    @Override
    public void updateEstadoEjemplar(String isbn) throws SQLException {
        try {
            connection = sqlConnection.getConnection();

            String sql = "UPDATE ejemplares SET Estado = 'NoDisponib' WHERE LibroISBN = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

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
