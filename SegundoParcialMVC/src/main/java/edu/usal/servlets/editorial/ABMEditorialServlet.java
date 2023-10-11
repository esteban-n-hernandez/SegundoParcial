package edu.usal.servlets.editorial;

import edu.usal.biblioteca.dao.interfaz.EditorialDAO;
import edu.usal.biblioteca.dominio.Editorial;
import edu.usal.biblioteca.factory.EditorialFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/abmEditoriales")
public class ABMEditorialServlet extends HttpServlet {

    EditorialDAO editorialDAO = EditorialFactory.getEditorialDAO("DB");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Editorial> listaEditoriales = editorialDAO.getAllEditoriales();
        request.setAttribute("listaEditoriales", listaEditoriales);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmEditoriales.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "agregar":
                Editorial nuevaEditorial = new Editorial();
                nuevaEditorial.setNombreEditorial(request.getParameter("nombre"));
                nuevaEditorial.setDireccion(request.getParameter("direccion"));
                nuevaEditorial.setEmail(request.getParameter("email"));
                nuevaEditorial.setPaginaWeb(request.getParameter("paginaweb"));
                nuevaEditorial.setNumeroDeContacto(request.getParameter("numerocontacto"));

                editorialDAO.insertEditorial(nuevaEditorial);
                break;
            case "editar":
                try {
                    Editorial editorialExistente = editorialDAO.getEditorial(request.getParameter("nombre"));

                    editorialExistente.setNombreEditorial(request.getParameter("nombre"));
                    editorialExistente.setDireccion(request.getParameter("direccion"));
                    editorialExistente.setEmail(request.getParameter("email"));
                    editorialExistente.setPaginaWeb(request.getParameter("paginaweb"));
                    editorialExistente.setNumeroDeContacto(request.getParameter("numerocontacto"));


                    editorialDAO.updateEditorial(editorialExistente);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "eliminar":
                try {
                    editorialDAO.deleteEditorial(request.getParameter("nombre"));
                    break;
                } catch (SQLException e) {
                    request.setAttribute("errorMessage", e.getMessage());
                }
        }

        doGet(request, response);
    }
}
