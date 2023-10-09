package edu.usal.biblioteca.dominio;

public class Administrativo extends Persona {
    private String legajo;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void aprobarReserva(Reserva reserva) {
        // lógica para aprobar una reserva
    }

    public void agregarLibro(Libro libro) {
        // lógica para agregar un libro a la biblioteca
    }

    public void contactarCliente(Cliente cliente) {
        // lógica para contactar a un cliente
    }

    public void agregarCliente(Cliente cliente) {
        // lógica para agregar un cliente a la biblioteca
    }

    public void eliminarCliente(Cliente cliente) {
        // lógica para eliminar un cliente de la biblioteca
    }

    public void eliminarLibro(Libro libro) {
        // lógica para eliminar un libro de la biblioteca
    }
}