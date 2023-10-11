package edu.usal.biblioteca;

import edu.usal.biblioteca.dao.impl.LibroDAOImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello");
        LibroDAOImpl libroDAO = new LibroDAOImpl();
        libroDAO.getAllLibros();
    }
}
