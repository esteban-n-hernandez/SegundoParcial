package edu.usal.biblioteca.factory;

import edu.usal.biblioteca.dao.interfaz.PersonaDAO;

public class PersonaFactory {


    public static PersonaDAO getPersonaDAO(String implementacion) {
        if (implementacion.equalsIgnoreCase("DB")) {
            return new PersonaDAO() {
            };
        }
        throw new RuntimeException("Imple no encontrada.");
    }
}
