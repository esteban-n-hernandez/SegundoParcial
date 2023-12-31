package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Clientes";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDni(resultSet.getString("dni"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listaClientes;
    }

    @Override
    public Cliente getCliente(String dni) {
        Cliente cliente = new Cliente();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Clientes WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dni);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDni(resultSet.getString("dni"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return cliente;
    }

    @Override
    public void insertCliente(Cliente cliente) {
        try {
            connection = sqlConnection.getConnection();

            String sql = "INSERT INTO Clientes (`DNI`, `Nombre`, `Apellido`, `Telefono`, `Email`) VALUES (?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getDni());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        try {
            connection = sqlConnection.getConnection();

            String sql = "UPDATE Clientes SET Nombre = ? , Apellido = ? , Telefono = ? , Email = ? WHERE dni = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getDni());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteCliente(String dni) throws SQLException {
        try {
            connection = sqlConnection.getConnection();

            String sql = "DELETE FROM Clientes WHERE dni = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dni);

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