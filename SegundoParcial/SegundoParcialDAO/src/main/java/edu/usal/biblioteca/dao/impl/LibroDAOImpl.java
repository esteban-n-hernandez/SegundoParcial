package edu.usal.biblioteca.dao.impl;

import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.dominio.Libro;
import edu.usal.biblioteca.util.SQLConnection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    SQLConnection sqlConnection = new SQLConnection();

    @Override
    public List<Libro> getAllLibros() throws SQLException {
        Statement st = sqlConnection.getConnectiion().createStatement();
             st.executeQuery("SELECT * FROM biblioteca.clientes;");

        return null;
    }

    @Override
    public Libro getLibro(String isbn) {
        return null;
    }

    @Override
    public void updateLibro(Libro libro) {

    }

    @Override
    public void deleteLibro(Libro libro) {

    }
}