package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.LibroDAOImpl;
import edu.usal.biblioteca.dao.interfaz.LibroDAO;

public class LibrosFactory {

    public static LibroDAO getLibroDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new LibroDAOImpl();
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
