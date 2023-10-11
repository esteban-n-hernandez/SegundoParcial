package edu.usal.biblioteca.dao.interfaz;

import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.dominio.Editorial;

import java.sql.SQLException;
import java.util.List;

public interface EditorialDAO {

    public List<Editorial> getAllEditoriales();

    public Editorial getEditorial(String nombreEditorial);

    public void insertEditorial(Editorial editorial);

    public void updateEditorial(Editorial editorial) throws SQLException;

    public void deleteEditorial(String nombreEditorial) throws SQLException;

}
