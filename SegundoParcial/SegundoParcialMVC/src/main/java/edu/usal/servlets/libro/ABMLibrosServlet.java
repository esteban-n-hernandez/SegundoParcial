package edu.usal.servlets.libro;

import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.impl.LibroDAOImpl;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.dominio.Libro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/abmLibros")
public class ABMLibrosServlet extends HttpServlet {
    private LibroDAO libroDAO = new LibroDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Libro> listaLibros = null;
        try {
            listaLibros = libroDAO.getAllLibros();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaLibros", listaLibros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmLibros.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí puedes manejar la lógica para agregar, editar y eliminar libros
    }
}

