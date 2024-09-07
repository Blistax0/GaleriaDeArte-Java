package com.felipe.galeriadearte;

import java.io.*;

public class GaleriaDeArte {
    public static void main(String[] args) throws IOException{
        
        Galeria galeria = new Galeria();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
        Sala sala1 = new Sala("Sala 1");
        sala1.agregarObra(new Obra("a", "autor a", 2000, 25000));
        sala1.agregarObra(new Obra("b", "autor b", 2020, 259000));
        
        Sala sala2 = new Sala("Sala 2");
        sala2.agregarObra(new Obra("c", "autor c", 1987, 25098));
        sala2.agregarObra(new Obra("d", "autor d", 1992, 259700));
        
        Sala sala3 = new Sala("Sala 3");
        sala3.agregarObra(new Obra("e", "autor e", 1886, 250898));
        sala3.agregarObra(new Obra("f", "autor f", 1925, 259260));
        
        galeria.agregarSala(sala1);
        galeria.agregarSala(sala2);        
        galeria.agregarSala(sala3);
        
        while (true) {
            System.out.println("1. Agregar Sala");
            System.out.println("2. Agregar obra a una sala");
            System.out.println("3. Mostrar galeria");
            System.out.println("4. Salir");
            
            String opcion = lector.readLine();
            
            if (opcion.equals("1")){
                System.out.println("Ingrese el nombre de la nueva sala:");
                String nombreSala = lector.readLine();
                Sala nuevaSala = new Sala(nombreSala);
                galeria.agregarSala(nuevaSala);
                System.out.println("La sala ha sido creada con exito (Presione"
                        + "ENTER para continuar...)");
            } 
            
            else if (opcion.equals("2")) {
                System.out.println("Ingrese el nombre de la sala a modificar:");
                String nombreSala = lector.readLine();
                Sala sala = galeria.getSala(nombreSala);
                
                if (sala != null) {
                    System.out.println("Ingrese el titulo de la obra: ");
                    String titulo = lector.readLine();
                    System.out.println("Ingrese el nombre del artista: ");
                    String artista = lector.readLine();
                    System.out.println("Ingrese el año del artista: ");
                    int year = Integer.parseInt(lector.readLine());
                    System.out.println();
                    int precio = Integer.parseInt(lector.readLine());
                    
                    Obra nuevaObra = new Obra(titulo, artista, year, precio);
                    sala.agregarObra(nuevaObra);
                }
                else {
                    System.out.println("Sala no encontrada. (Presione ENTER "
                            + "para continuar...)");
                }
            }
            
            else if (opcion.equals("3")) {
                galeria.mostrarGaleria();
            }
            else if (opcion.equals("4")){
                break;
            }
            else {
                System.out.println("Opción inválida...");
            }
        }
    }
}
