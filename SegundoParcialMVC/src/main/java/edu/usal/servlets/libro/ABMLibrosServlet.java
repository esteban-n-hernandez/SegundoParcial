package edu.usal.servlets.libro;

import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.impl.LibroDAOImpl;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.dominio.Editorial;
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
        String action = request.getParameter("action");
        if (action.equals("agregar")) {
            Libro nuevoLibro = new Libro();
            nuevoLibro.setTitulo(request.getParameter("titulo"));
            nuevoLibro.setIsbn(request.getParameter("isbn"));
            nuevoLibro.setCantidadDePaginas(Integer.parseInt(request.getParameter("cantidadDePaginas")));
            nuevoLibro.setGenero(request.getParameter("genero"));
            nuevoLibro.setEdicion(request.getParameter("edicion"));
            Autor autor = new Autor();
            autor.setNombre(request.getParameter("nombreAutor"));
            autor.setApellido(request.getParameter("apellidoAutor"));
            autor.setNacionalidad(request.getParameter("nacionalidadAutor"));
            nuevoLibro.setAutor(autor);

            libroDAO.insertLibro(nuevoLibro);
        } else if (action.equals("editar")) {
            // Aquí puedes manejar la lógica para editar un libro
        } else if (action.equals("eliminar")) {
            // Aquí puedes manejar la lógica para eliminar un libro
        }
        doGet(request, response);
    }

}

