package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.AdministrativoDAO;
import edu.usal.biblioteca.dominio.Administrativo;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoDAOImpl implements AdministrativoDAO {


    Connection connection = null;
    SQLConnection sqlConnection = new SQLConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Administrativo> getAllAdministrativos() {
        List<Administrativo> listaAdministrativos = new ArrayList<>();

        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Administrativos";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Administrativo administrativo = new Administrativo();
                administrativo.setNombre(resultSet.getString("nombre"));
                administrativo.setApellido(resultSet.getString("apellido"));
                administrativo.setDni(resultSet.getString("dni"));
                administrativo.setTelefono(resultSet.getString("telefono"));
                administrativo.setLegajo(resultSet.getString("legajo"));
                listaAdministrativos.add(administrativo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listaAdministrativos;
    }

    @Override
    public Administrativo getAdministrativo(String dni) {
        Administrativo administrativo = new Administrativo();
        try {
            connection = sqlConnection.getConnection();
            String sql = "SELECT * FROM Administrativos WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dni);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                administrativo.setNombre(resultSet.getString("nombre"));
                administrativo.setApellido(resultSet.getString("apellido"));
                administrativo.setDni(resultSet.getString("dni"));
                administrativo.setTelefono(resultSet.getString("telefono"));
                administrativo.setLegajo(resultSet.getString("legajo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return administrativo;
    }

    @Override
    public void insertAdministrativo(Administrativo administrativo) {
        try {
            connection = sqlConnection.getConnection();

            String sql = "INSERT INTO Administrativos (`DNI`, `Nombre`, `Apellido`, `Telefono`, `Legajo`) VALUES (?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, administrativo.getDni());
            preparedStatement.setString(2, administrativo.getNombre());
            preparedStatement.setString(3, administrativo.getApellido());
            preparedStatement.setString(4, administrativo.getTelefono());
            preparedStatement.setString(5, administrativo.getLegajo());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateAdministrativo(Administrativo administrativo) {
        try {
            connection = sqlConnection.getConnection();

            String sql = "UPDATE Administrativos SET Nombre = ? , Apellido = ? , Telefono = ? , Legajo = ? WHERE dni = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, administrativo.getNombre());
            preparedStatement.setString(2, administrativo.getApellido());
            preparedStatement.setString(3, administrativo.getTelefono());
            preparedStatement.setString(4, administrativo.getLegajo());
            preparedStatement.setString(5, administrativo.getDni());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteAdministrativo(String dni) {
        try {
            connection = sqlConnection.getConnection();

            String sql = "DELETE FROM Administrativos WHERE dni = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dni);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
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
