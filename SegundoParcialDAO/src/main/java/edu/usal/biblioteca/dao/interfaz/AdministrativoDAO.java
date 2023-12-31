package edu.usal.biblioteca.dao.interfaz;

import edu.usal.biblioteca.dominio.Administrativo;

import java.util.List;

public interface AdministrativoDAO {

    public List<Administrativo> getAllAdministrativos();

    public Administrativo getAdministrativo(String dni);

    public void insertAdministrativo(Administrativo administrativo);

    public void updateAdministrativo(Administrativo administrativo);

    public void deleteAdministrativo(String dni);
}
