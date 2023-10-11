package edu.usal.biblioteca.dao.interfaz;


import edu.usal.biblioteca.dominio.Ejemplar;

import java.sql.SQLException;
import java.util.List;

public interface EjemplarDAO {

    List<Ejemplar> getAllEjemplares();

    void updateEstadoEjemplar(String isbn) throws SQLException;



}
