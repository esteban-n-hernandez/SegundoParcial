package edu.usal.servlets.reserva;

import edu.usal.biblioteca.dao.interfaz.EjemplarDAO;
import edu.usal.biblioteca.dao.interfaz.ReservaDAO;
import edu.usal.biblioteca.dominio.Reserva;
import edu.usal.biblioteca.factory.EjemplarFactory;
import edu.usal.biblioteca.factory.ReservaFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/abmReserva")
public class ReservaServlet extends HttpServlet {

    EjemplarDAO ejemplarDAO = EjemplarFactory.getEjemplarDAO("DB");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReservaDAO reservaDAO = ReservaFactory.getReservaDAO("DB");
        List<Reserva> listaReservas = reservaDAO.getAllReservas();
        request.setAttribute("listaReservas", listaReservas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmReserva.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ReservaDAO reservaDAO = ReservaFactory.getReservaDAO("DB");

        switch (action) {
            case "agregar":
                try {
                    ejemplarDAO.updateEstadoEjemplar(request.getParameter("isbn"));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("abmReserva");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "editar":
                Reserva reservaExistente = reservaDAO.getReserva(request.getParameter("dni"));

                reservaExistente.setClienteDNI(request.getParameter("nombre"));
                reservaExistente.setLibroISBN(request.getParameter("apellido"));

                java.sql.Date fechaDevolucion = java.sql.Date.valueOf(request.getParameter("fechaDevolucion"));
                java.sql.Date fechaReserva = java.sql.Date.valueOf(request.getParameter("fechaReserva"));
                reservaExistente.setFechaDeReserva(fechaReserva);
                reservaExistente.setFechaDeDevolucion(fechaDevolucion);

                reservaDAO.updateReserva(reservaExistente, reservaExistente.getId());
                break;
            case "eliminar":
                break;
        }

        response.sendRedirect("abmReserva");
    }
}
