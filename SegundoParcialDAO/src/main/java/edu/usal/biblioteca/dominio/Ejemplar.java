package edu.usal.biblioteca.dominio;


public class Ejemplar {
    private String id;
    private String libroISBN;
    private Integer editorialID;
    private String estado;

    public String getLibroISBN() {
        return libroISBN;
    }

    public void setLibroISBN(String libroISBN) {
        this.libroISBN = libroISBN;
    }

    public Integer getEditorialID() {
        return editorialID;
    }

    public void setEditorialID(Integer editorialID) {
        this.editorialID = editorialID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}