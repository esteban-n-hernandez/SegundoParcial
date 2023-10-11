package edu.usal.biblioteca.dominio;

import java.util.Date;

public class Reserva {
    private Integer id;
    private String libroISBN;
    private String clienteDNI;
    private Date fechaDeReserva;
    private Date fechaDeDevolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibroISBN() {
        return libroISBN;
    }

    public void setLibroISBN(String libroISBN) {
        this.libroISBN = libroISBN;
    }

    public String getClienteDNI() {
        return clienteDNI;
    }

    public void setClienteDNI(String clienteDNI) {
        this.clienteDNI = clienteDNI;
    }

    public Date getFechaDeReserva() {
        return fechaDeReserva;
    }

    public void setFechaDeReserva(Date fechaDeReserva) {
        this.fechaDeReserva = fechaDeReserva;
    }

    public Date getFechaDeDevolucion() {
        return fechaDeDevolucion;
    }

    public void setFechaDeDevolucion(Date fechaDeDevolucion) {
        this.fechaDeDevolucion = fechaDeDevolucion;
    }

}