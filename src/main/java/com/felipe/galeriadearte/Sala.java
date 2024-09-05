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
    
}
