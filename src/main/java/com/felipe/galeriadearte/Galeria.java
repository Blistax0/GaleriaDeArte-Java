package com.felipe.galeriadearte;

import java.util.HashMap;
import java.util.Map;

public class Galeria {
    private Map<String, Sala> salasGaleria;
    
    public Galeria(){
        this.salasGaleria = new HashMap<>();
    }
    
    public void agregarSala(Sala sala){
        salasGaleria.put(sala.getNombre(), sala);
    }
    
    public Sala getSala(String nombre) {
        return salasGaleria.get(nombre);
    }    
    
    public void mostrarGaleria(){
        if (salasGaleria.isEmpty()){
            System.out.println("La galeria no tiene salas.");
        }
        else {
            for (Sala sala : salasGaleria.values()){
            System.out.println("Sala: " + sala.getNombre());
            sala.mostrarObras();
            }
        }
    }
}