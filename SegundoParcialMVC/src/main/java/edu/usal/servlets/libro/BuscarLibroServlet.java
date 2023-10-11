package edu.usal.servlets.libro;

import edu.usal.biblioteca.dao.interfaz.LibroDAO;
import edu.usal.biblioteca.dao.interfaz.ReservaDAO;
import edu.usal.biblioteca.dominio.Libro;
import edu.usal.biblioteca.dominio.Reserva;
import edu.usal.biblioteca.factory.LibrosFactory;
import edu.usal.biblioteca.factory.ReservaFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@WebServlet("/busquedaLibros")
public class BuscarLibroServlet extends HttpServlet {

    LibroDAO dao = LibrosFactory.getLibroDAO("DB");
    ReservaDAO reservaDAO = ReservaFactory.getReservaDAO("DB");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Libro> listaLibros = null;

        try {
            if (query != null && !query.isEmpty()) {
                listaLibros = dao.getAllLibrosPorISBNorTitulo(query);
            } else {
                listaLibros = dao.getAllLibrosDisponibles();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("listaLibros", listaLibros);
        request.getRequestDispatcher("busquedaLibros.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clienteDNI = request.getParameter("clienteDNI");
        String libroISBN = request.getParameter("LibroISBN");

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setClienteDNI(clienteDNI);
        nuevaReserva.setLibroISBN(libroISBN);

        // Establece la fecha de reserva como la fecha actual (sysdate)
        Calendar calendar = Calendar.getInstance();
        Date fechaDeReserva = calendar.getTime();
        nuevaReserva.setFechaDeReserva(fechaDeReserva);

        // Establece la fecha de devolución como 5 días hábiles después
        int diasHabiles = 5;
        while (diasHabiles > 0) {
            calendar.add(Calendar.DATE, 1);
            // Verifica si el día es un día hábil (no es sábado ni domingo)
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                diasHabiles--;
            }
        }
        Date fechaDeDevolucion = calendar.getTime();
        nuevaReserva.setFechaDeDevolucion(fechaDeDevolucion);

        try {
            reservaDAO.insertReserva(nuevaReserva);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("busquedaLibros.jsp").forward(request, response);
    }
}
