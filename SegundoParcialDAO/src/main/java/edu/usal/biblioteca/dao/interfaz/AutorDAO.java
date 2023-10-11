package edu.usal.biblioteca.dao.interfaz;


import edu.usal.biblioteca.dominio.Autor;

import java.sql.SQLException;
import java.util.List;

public interface AutorDAO {

    List<Autor> getAllAutores();

    Autor getAutor(String isbn);

    void insertAutor(Autor libro) throws SQLException;

    void updateAutor(Autor libro);

    void deleteAutor(Integer id) throws SQLException;
}
