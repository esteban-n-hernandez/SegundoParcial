package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.EjemplarDAOImpl;
import edu.usal.biblioteca.dao.interfaz.EjemplarDAO;

public class EjemplarFactory {

    public static EjemplarDAO getEjemplarDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new EjemplarDAOImpl();
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
