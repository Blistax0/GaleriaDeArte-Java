package com.felipe.galeriadearte;

import java.io.*;
import java.util.*;

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
    
    public Map<String, Sala> getSalasGaleria() {
        return salasGaleria;
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

    public void guardarEnArchivo(String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Sala sala : salasGaleria.values()) {
                for (Obra obra : sala.getObras()) {
                    writer.write(sala.getNombre() + "," + obra.getTitulo() + "," + obra.getArtista() + "," +
                            obra.getYear() + "," + obra.getPrecio());
                    writer.newLine();
                }
            }
        }
    }

    public void cargarDesdeArchivo(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(",");
                    String nombreSala = partes[0];
                    String tituloObra = partes[1];
                    String artistaObra = partes[2];
                    int yearObra = Integer.parseInt(partes[3]);
                    int precioObra = Integer.parseInt(partes[4]);

                    Sala sala = getSala(nombreSala);
                    if (sala == null) {
                        sala = new Sala(nombreSala);
                        agregarSala(sala);
                    }
                    sala.agregarObra(new Obra(tituloObra, artistaObra, yearObra, precioObra));
                }
            }
        }
    }
}