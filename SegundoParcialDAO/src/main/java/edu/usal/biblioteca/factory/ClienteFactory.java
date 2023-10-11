package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;

public class ClienteFactory {

    public static ClienteDAO getClienteDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new ClienteDAOImpl();
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
