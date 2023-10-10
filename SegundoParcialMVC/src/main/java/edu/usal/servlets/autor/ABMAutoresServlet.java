package edu.usal.servlets.autor;

import edu.usal.biblioteca.dao.interfaz.AutorDAO;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.factory.AutorFactory;
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

@WebServlet("/abmAutores")
public class ABMAutoresServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AutorDAO autorDAO = AutorFactory.getAutorDAO("DB");
        List<Autor> listaAutores = autorDAO.getAllAutores();
        request.setAttribute("listaAutores", listaAutores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmAutores.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        AutorDAO autorDAO = AutorFactory.getAutorDAO("DB");

        switch (action) {
            case "agregar":
                try {
                    Autor nuevoAutor = new Autor();
                    nuevoAutor.setNombre(request.getParameter("nombre"));
                    nuevoAutor.setApellido(request.getParameter("apellido"));
                    nuevoAutor.setNacionalidad(request.getParameter("nacionalidad"));

                    autorDAO.insertAutor(nuevoAutor);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "editar":
                Autor autorExistente = autorDAO.getAutor(request.getParameter("dni"));

                autorExistente.setNombre(request.getParameter("nombre"));
                autorExistente.setApellido(request.getParameter("apellido"));
                autorExistente.setNacionalidad(request.getParameter("nacionalidad"));

                autorDAO.updateAutor(autorExistente);
                break;
            case "eliminar":
                try {
                    autorDAO.deleteAutor(Integer.valueOf(request.getParameter("id")));
                } catch (SQLException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                }
                break;

        }
        doGet(request, response);
    }
}
