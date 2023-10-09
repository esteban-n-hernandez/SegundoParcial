package edu.usal.servlets.reserva;

import edu.usal.biblioteca.dao.impl.LibroDAOImpl;
import edu.usal.biblioteca.dao.impl.ReservaDAOImpl;
import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dao.interfaz.ReservaDAO;
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

@WebServlet("/reservaLibros")
public class ReservaLibrosServlet extends HttpServlet {
    private LibroDAO libroDAO = new LibroDAOImpl();
    private ReservaDAO reservaDAO = new ReservaDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tituloOIsbn = request.getParameter("tituloOIsbn");
        List<Libro> listaLibros = null;
        try {
            listaLibros = libroDAO.getAllLibros();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaLibros", listaLibros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reservaLibros.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí puedes manejar la lógica para reservar un libro
    }
}

