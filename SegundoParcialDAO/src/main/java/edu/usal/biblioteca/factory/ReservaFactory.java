package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.AutorDAOImpl;
import edu.usal.biblioteca.dao.impl.ReservaDAOImpl;
import edu.usal.biblioteca.dao.interfaz.AutorDAO;
import edu.usal.biblioteca.dao.interfaz.ReservaDAO;

public class ReservaFactory {

    public static ReservaDAO getReservaDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new ReservaDAOImpl();
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
