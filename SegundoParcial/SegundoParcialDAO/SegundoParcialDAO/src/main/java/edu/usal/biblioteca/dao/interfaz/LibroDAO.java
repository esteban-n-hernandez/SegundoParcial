package edu.usal.biblioteca.dao.interfaz;

import edu.usal.biblioteca.dominio.Libro;

import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {

    public List<Libro> getAllLibros() throws SQLException;

    public Libro getLibro(String isbn);

    public void insertLibro(Libro libro);

    public void updateLibro(Libro libro);

    public void deleteLibro(String isbn);

}
