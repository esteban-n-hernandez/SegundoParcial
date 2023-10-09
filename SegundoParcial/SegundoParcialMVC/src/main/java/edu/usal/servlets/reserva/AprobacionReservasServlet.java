package edu.usal.servlets.reserva;

import edu.usal.biblioteca.dao.impl.ReservaDAOImpl;
import edu.usal.biblioteca.dao.interfaz.ReservaDAO;
import edu.usal.biblioteca.dominio.Reserva;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/aprobacionReservas")
public class AprobacionReservasServlet extends HttpServlet {
    private ReservaDAO reservaDAO = new ReservaDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reserva> listaReserva = null;
        request.setAttribute("listaLibros", listaReserva);
        RequestDispatcher dispatcher = request.getRequestDispatcher("abmReservas.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Manejo de lógica para agregar, editar y eliminar reservas
    }
}

