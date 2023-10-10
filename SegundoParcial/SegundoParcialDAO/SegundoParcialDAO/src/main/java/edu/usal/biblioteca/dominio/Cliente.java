package edu.usal.biblioteca.dominio;

public class Cliente extends Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;

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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Libro buscarLibro(String tituloOIsbn) {
        // lógica para buscar un libro por título o ISBN
        return null;
    }

    public void devolverLibro(Libro libro) {
        // lógica para devolver un libro
    }
}