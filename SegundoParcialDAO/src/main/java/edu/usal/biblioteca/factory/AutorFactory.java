package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.AutorDAOImpl;
import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.interfaz.AutorDAO;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;

public class AutorFactory {

    public static AutorDAO getAutorDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new AutorDAOImpl();
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
