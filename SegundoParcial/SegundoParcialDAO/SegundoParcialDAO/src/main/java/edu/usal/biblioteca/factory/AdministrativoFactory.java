package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.impl.AdministrativoDAOImpl;
import edu.usal.biblioteca.dao.interfaz.AdministrativoDAO;

public class AdministrativoFactory {

    public static AdministrativoDAO getAdministrativoDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new AdministrativoDAOImpl() {
            };
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
