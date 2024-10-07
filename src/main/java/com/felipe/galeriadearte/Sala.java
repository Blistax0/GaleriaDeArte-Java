package com.felipe.galeriadearte;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nombre;
    private List<Obra> obras;

    public Sala(String nombre) {
        this.nombre = nombre;
        this.obras = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarObra(Obra obra) {
        obras.add(obra);
    }
    
    public List<Obra> getObras() {
        return obras;
    }

    public void mostrarObras() {
        if (obras.isEmpty()) {
            System.out.println("No hay obras en esta sala.");
        } else {
            System.out.println("Obras en la sala '" + nombre + "':");
            for (Obra obra : obras) {
                System.out.println(" - '" + obra.getTitulo() + "' de '" 
                        + obra.getArtista() + "' (" + obra.getYear() + " -  $" + 
                        obra.getPrecio() + ")");
            }
        }
    }

    public void mostrarObras(int yearInicio, int yearFin) {
        boolean hayObras = false;
        for (Obra obra : obras) {
            if (obra.getYear() >= yearInicio && obra.getYear() <= yearFin) {
                if (!hayObras) {
                    System.out.println("Obras en la sala '" + nombre + "' entre los años " + yearInicio + " y " + yearFin + ":");
                    hayObras = true;
                }
                System.out.println(" - '" + obra.getTitulo() + "' de '" 
                        + obra.getArtista() + "' (" + obra.getYear() + " -  $" + 
                        obra.getPrecio() + ")");
            }
        }
        if (!hayObras) {
            System.out.println("No hay obras en esta sala dentro del rango de años especificado.");
        }
    }
    
    public Obra getObra(String titulo) {
        for (Obra obra : obras) {
            if (obra.getTitulo().equalsIgnoreCase(titulo)) {
                return obra;
            }
        }
        return null;
    }

    public void eliminarObra(Obra obra) {
        obras.remove(obra);
    }
}
