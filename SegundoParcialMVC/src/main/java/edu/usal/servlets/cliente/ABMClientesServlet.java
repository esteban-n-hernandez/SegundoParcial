package edu.usal.servlets.cliente;

import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.factory.ClienteFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/abmClientes")
public class ABMClientesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDAO clienteDAO = ClienteFactory.getClienteDAO("DB");
        List<Cliente> listaClientes = clienteDAO.getAllClientes();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmClientes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ClienteDAO clienteDAO = ClienteFactory.getClienteDAO("DB");

        switch (action) {
            case "agregar":
                // Crear un nuevo objeto Cliente con los datos del formulario
                Cliente nuevoCliente = new Cliente();
                nuevoCliente.setNombre(request.getParameter("nombre"));
                nuevoCliente.setApellido(request.getParameter("apellido"));
                nuevoCliente.setDni(request.getParameter("dni"));
                nuevoCliente.setTelefono(request.getParameter("telefono"));
                nuevoCliente.setEmail(request.getParameter("email"));

                // Agregar el nuevo cliente a la base de datos
                clienteDAO.insertCliente(nuevoCliente);
                break;
            case "editar":
                // Obtener el cliente existente de la base de datos
                Cliente clienteExistente = clienteDAO.getCliente(request.getParameter("dni"));

                // Actualizar los datos del cliente
                clienteExistente.setNombre(request.getParameter("nombre"));
                clienteExistente.setApellido(request.getParameter("apellido"));
                clienteExistente.setTelefono(request.getParameter("telefono"));
                clienteExistente.setEmail(request.getParameter("email"));

                // Guardar los cambios en la base de datos
                clienteDAO.updateCliente(clienteExistente);
                break;
            case "eliminar":
                try{
                    clienteDAO.deleteCliente(request.getParameter("dni"));
                    break;
                }catch (SQLException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                }
                // Eliminar el cliente de la base de datos

        }

        // Redirigir al usuario a la página de ABM Clientes
        doGet(request, response);
    }
}
