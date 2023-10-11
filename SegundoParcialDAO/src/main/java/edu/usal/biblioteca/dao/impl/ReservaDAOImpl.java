package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.ReservaDAO;
import edu.usal.biblioteca.dominio.Reserva;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {
    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Reserva> getAllReservas() {
        List<Reserva> listaReserva = new ArrayList<>();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Reservas";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reserva reserva = new Reserva();

                reserva.setClienteDNI(resultSet.getString("clienteDNI"));
                reserva.setLibroISBN(resultSet.getString("LibroISBN"));
                reserva.setFechaDeReserva(resultSet.getDate("FechaDeReserva"));
                reserva.setFechaDeDevolucion(resultSet.getDate("FechaDeDevolucion"));
                listaReserva.add(reserva);
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
        return listaReserva;
    }

    @Override
    public Reserva getReserva(String dni) {
        Reserva reserva = new Reserva();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Reservas where ClienteDNI = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dni);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                reserva.setId(resultSet.getInt("id"));
                reserva.setClienteDNI(resultSet.getString("clienteDNI"));
                reserva.setLibroISBN(resultSet.getString("LibroISBN"));
                reserva.setFechaDeReserva(resultSet.getDate("FechaDeReserva"));
                reserva.setFechaDeDevolucion(resultSet.getDate("FechaDeDevolucion"));
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
        return reserva;
    }

    @Override
    public void insertReserva(Reserva reserva) {
        connection = sqlConnection.getConnection();
        try {
            CallableStatement stmt = connection.prepareCall("CALL InsertarEnReservas(?, ?, ?, ?)");

            stmt.setString(1, reserva.getClienteDNI());
            stmt.setString(2, reserva.getLibroISBN());
            stmt.setDate(3, new java.sql.Date(reserva.getFechaDeReserva().getTime()));
            stmt.setDate(4, new java.sql.Date(reserva.getFechaDeDevolucion().getTime()));

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

    @Override
    public void updateReserva(Reserva reserva, Integer id) {
        connection = sqlConnection.getConnection();
        try {
            CallableStatement stmt = connection.prepareCall("CALL ActualizarReservas(?,?,?,?,?;");

            stmt.setInt(1, id);
            stmt.setString(2, reserva.getClienteDNI());
            stmt.setString(3, reserva.getLibroISBN());
            stmt.setDate(4, (java.sql.Date) reserva.getFechaDeReserva());
            stmt.setDate(5, (java.sql.Date) reserva.getFechaDeDevolucion());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReserva(Reserva id) throws SQLException {

    }
}