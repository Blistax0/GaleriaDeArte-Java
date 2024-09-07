package com.felipe.galeriadearte;

import java.util.ArrayList;

public class Sala {
    private String nombre;
    private ArrayList<Obra> obras;
    
    public Sala(String nombre) {
        this.nombre = nombre;
        this.obras = new ArrayList<>();
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void agregarObra(Obra obra) {
        obras.add(obra);
    }
    
    public void mostrarObras() {
        if (obras.isEmpty()) {
            System.out.println("No hay obras en esta sala.");
        } 
        else {
            System.out.println("Obras en la sala " + nombre + ":");
            for (Obra obra : obras) {
                System.out.println(" - " + obra.getTitulo() + " de " 
                        + obra.getArtista() + " (" + obra.getYear() + " -  $" + 
                        obra.getPrecio() + ")");
            }
        }
    }
}
