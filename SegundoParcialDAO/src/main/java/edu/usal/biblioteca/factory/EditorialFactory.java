package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.AdministrativoDAOImpl;
import edu.usal.biblioteca.dao.impl.EditorialDAOImpl;
import edu.usal.biblioteca.dao.interfaz.AdministrativoDAO;
import edu.usal.biblioteca.dao.interfaz.EditorialDAO;

public class EditorialFactory {

    public static EditorialDAO getEditorialDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new EditorialDAOImpl();
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
