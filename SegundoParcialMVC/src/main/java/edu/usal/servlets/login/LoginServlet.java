package edu.usal.servlets.login;

import edu.usal.biblioteca.dao.impl.AdministrativoDAOImpl;
import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.interfaz.AdministrativoDAO;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dominio.Administrativo;
import edu.usal.biblioteca.dominio.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ClienteDAO clienteDAO = new ClienteDAOImpl();
    private AdministrativoDAO administrativoDAO = new AdministrativoDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String tipoUsuario = request.getParameter("tipoUsuario");

        if (tipoUsuario != null && dni != null) {
            if (tipoUsuario.equals("cliente")) {
                Cliente cliente = clienteDAO.getCliente(dni);
                if (cliente != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", cliente);
                    response.sendRedirect("menuPrincipal.jsp");
                }
            } else if (tipoUsuario.equals("administrativo")) {
                Administrativo administrativo = administrativoDAO.getAdministrativo(dni);
                if (administrativo != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", administrativo);
                    response.sendRedirect("menuPrincipalAdministrativo.jsp");
                }
            }
        }
    }
}
