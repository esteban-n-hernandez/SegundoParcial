package edu.usal.biblioteca.dao.interfaz;

import edu.usal.biblioteca.dominio.Cliente;

import java.util.List;

public interface ClienteDAO {

    public List<Cliente> getAllClientes();

    public Cliente getCliente(String dni);

    public void insertCliente(Cliente cliente);

    public void updateCliente(Cliente cliente);

    public void deleteCliente(String dni);

}
