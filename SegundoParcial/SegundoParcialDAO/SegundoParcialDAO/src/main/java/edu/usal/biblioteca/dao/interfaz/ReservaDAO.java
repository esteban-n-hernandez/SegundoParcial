package edu.usal.biblioteca.dao.interfaz;

import edu.usal.biblioteca.dominio.Autor;
import edu.usal.biblioteca.dominio.Reserva;

import java.sql.SQLException;
import java.util.List;

public interface ReservaDAO {

    List<Reserva> getAllReservas();

    Reserva getReserva(String dni);

    void insertReserva(Reserva reserva) throws SQLException;

    void updateReserva(Reserva reserva, Integer id);

    void deleteReserva(Reserva id) throws SQLException;

}
