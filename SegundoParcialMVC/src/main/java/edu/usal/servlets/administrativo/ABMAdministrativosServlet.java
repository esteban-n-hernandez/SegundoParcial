package edu.usal.servlets.administrativo;

import edu.usal.biblioteca.dao.interfaz.AdministrativoDAO;
import edu.usal.biblioteca.dominio.Administrativo;
import edu.usal.biblioteca.factory.AdministrativoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/abmAdministrativos")
public class ABMAdministrativosServlet extends HttpServlet {
    AdministrativoDAO administrativoDAO = AdministrativoFactory.getAdministrativoDAO("DB");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Administrativo> listaAdministrativo = administrativoDAO.getAllAdministrativos();
        request.setAttribute("listaAdministrativos", listaAdministrativo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmAdministrativos.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        AdministrativoDAO administrativoDAO = AdministrativoFactory.getAdministrativoDAO("DB");

        switch (action) {
            case "agregar":
                Administrativo nuevoAdministrativo = new Administrativo();
                nuevoAdministrativo.setNombre(request.getParameter("nombre"));
                nuevoAdministrativo.setApellido(request.getParameter("apellido"));
                nuevoAdministrativo.setDni(request.getParameter("dni"));
                nuevoAdministrativo.setTelefono(request.getParameter("telefono"));
                nuevoAdministrativo.setLegajo(request.getParameter("legajo"));

                // Agregar el nuevo administrativo a la base de datos
                administrativoDAO.insertAdministrativo(nuevoAdministrativo);
                break;
            case "editar":
                Administrativo administrativoExistente = administrativoDAO.getAdministrativo(request.getParameter("dni"));

                administrativoExistente.setNombre(request.getParameter("nombre"));
                administrativoExistente.setApellido(request.getParameter("apellido"));
                administrativoExistente.setTelefono(request.getParameter("telefono"));
                administrativoExistente.setLegajo(request.getParameter("legajo"));
                administrativoExistente.setDni(request.getParameter("dni"));

                administrativoDAO.updateAdministrativo(administrativoExistente);
                break;
            case "eliminar":
                administrativoDAO.deleteAdministrativo(request.getParameter("dni"));
                break;
        }

        doGet(request, response);
    }

}
