package com.felipe.galeriadearte;

public class Obra {
    private String titulo;
    private String artista;
    private int year;
    private int precio;
    
    public Obra(String titulo, String artista, int year, int precio) {
        this.titulo = titulo;
        this.artista = artista;
        this.year = year;
        this.precio = precio;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    
    
}
