package edu.usal.biblioteca.dominio;

public class Cliente extends Persona {
    private String email;

    public void reservarLibro(Libro libro) {
        // lógica para reservar un libro
    }

    public Libro buscarLibro(String tituloOIsbn) {
        // lógica para buscar un libro por título o ISBN
        return null;
    }

    public void devolverLibro(Libro libro) {
        // lógica para devolver un libro
    }
}