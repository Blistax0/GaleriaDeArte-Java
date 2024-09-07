package com.felipe.galeriadearte;

import java.util.HashMap;

public class Galeria {
    private HashMap<String, Sala> SalasGaleria;
    
    public void Galeria(){
        this.SalasGaleria = new HashMap<>();
    }
    
    public void agregarSala(Sala sala){
        SalasGaleria.put(sala.getNombre(), sala);
    }
    
    public Sala getSala(String nombre) {
        return SalasGaleria.get(nombre);
    }    
    
}