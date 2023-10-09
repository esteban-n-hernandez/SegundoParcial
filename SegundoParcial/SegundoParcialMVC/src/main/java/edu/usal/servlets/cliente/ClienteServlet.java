package edu.usal.servlets.cliente;

import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dominio.Cliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/listadoClientes")
public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = clienteDAO.getAllClientes();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listadoClientes.jsp");
        dispatcher.forward(request, response);
    }
}
