package com.felipe.galeriadearte;

import java.util.HashMap;

public class Galeria {
    private HashMap<String, Sala> SalasGaleria;
    
    public void Galeria(){
        SalasGaleria = new HashMap<>();
    }
    
    public void agregarSala(Sala sala){
        SalasGaleria.put(sala.getNombre(), sala);
    }
    
//    public void mostrarGaleria()
}
