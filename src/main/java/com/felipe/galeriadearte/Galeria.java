package com.felipe.galeriadearte;

import java.util.HashMap;

public class Galeria {
    private HashMap<String, Sala> salasGaleria;
    
    public void Galeria(){
        this.salasGaleria = new HashMap<>();
    }
    
    public void agregarSala(Sala sala){
        salasGaleria.put(sala.getNombre(), sala);
    }
    
    public Sala getSala(String nombre) {
        return salasGaleria.get(nombre);
    }    
    
    public void mostrarGaleria(){
        for (Sala sala : salasGaleria.values()){
            System.out.println("Sala: " + sala.getNombre());
            sala.mostrarObras();
        }
    }
}