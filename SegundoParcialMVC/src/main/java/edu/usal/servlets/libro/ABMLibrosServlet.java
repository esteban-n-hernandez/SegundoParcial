package edu.usal.servlets.libro;

import edu.usal.biblioteca.dao.impl.ClienteDAOImpl;
import edu.usal.biblioteca.dao.impl.LibroDAOImpl;
import edu.usal.biblioteca.dao.interfaz.AutorDAO;
import edu.usal.biblioteca.dao.interfaz.ClienteDAO;
import edu.usal.biblioteca.dao.interfaz.EditorialDAO;
import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.dominio.Cliente;
import edu.usal.biblioteca.dominio.Editorial;
import edu.usal.biblioteca.dominio.Libro;
import edu.usal.biblioteca.factory.AutorFactory;
import edu.usal.biblioteca.factory.EditorialFactory;
import edu.usal.biblioteca.factory.LibrosFactory;

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
    LibroDAO libroDAO = LibrosFactory.getLibroDAO("DB");
    AutorDAO autorDAO = AutorFactory.getAutorDAO("DB");
    EditorialDAO editorialDAO = EditorialFactory.getEditorialDAO("DB");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Libro> listaLibros = null;
        List<Autor> listaAutores = null;
        List<Editorial> listaEditoriales = null;
        try {
            listaLibros = libroDAO.getAllLibros();
            listaAutores = autorDAO.getAllAutores();
            listaEditoriales = editorialDAO.getAllEditoriales();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaLibros", listaLibros);
        request.setAttribute("listaAutores", listaAutores);
        request.setAttribute("listaEditoriales", listaEditoriales);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmLibros.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "agregar":

                Libro nuevoLibro = new Libro();
                nuevoLibro.setTitulo(request.getParameter("titulo"));
                nuevoLibro.setIsbn(request.getParameter("isbn"));
                nuevoLibro.setCantidadDePaginas(Integer.parseInt(request.getParameter("cantidadDePaginas")));
                nuevoLibro.setGenero(request.getParameter("genero"));
                nuevoLibro.setEdicion(request.getParameter("edicion"));

                Autor autor = new Autor();
                autor.setId(request.getParameter("autorID"));
                autor.setNombre(request.getParameter("nombreAutor"));
                autor.setApellido(request.getParameter("apellidoAutor"));
                autor.setNacionalidad(request.getParameter("nacionalidadAutor"));

                Editorial editorial = new Editorial();
                editorial.setId(Integer.valueOf(request.getParameter("editorialID")));
                editorial.setNombreEditorial(request.getParameter("nombre"));
                editorial.setDireccion(request.getParameter("direccion"));
                editorial.setPaginaWeb(request.getParameter("paginaweb"));
                editorial.setEmail(request.getParameter("email"));
                editorial.setNumeroDeContacto(request.getParameter("numeroDeContacto"));

                nuevoLibro.setEditorial(editorial);
                nuevoLibro.setAutor(autor);

                libroDAO.insertLibro(nuevoLibro);
                break;
            case "editar":
                break;
            case "eliminar":
                try {
                    libroDAO.deleteLibro(request.getParameter("isbn"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        doGet(request, response);
    }

}

