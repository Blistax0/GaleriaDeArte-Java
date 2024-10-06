package com.felipe.galeriadearte;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GaleriaVentanaSwing extends JFrame {
    private Galeria galeria;
    private JTextArea textArea;

    public GaleriaVentanaSwing(Galeria galeria) {
        this.galeria = galeria;

        setTitle("Galería de Arte");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        // boton mostrar galeria
        JButton botonMostrarGaleria = new JButton("Mostrar Galería");
        botonMostrarGaleria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGaleria();
            }
        });
        panelBotones.add(botonMostrarGaleria);

        // boton agregar sala
        JButton botonAgregarSala = new JButton("Agregar Sala");
        botonAgregarSala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarSala();
            }
        });
        panelBotones.add(botonAgregarSala);

        // boton agregar obra
        JButton botonAgregarObra = new JButton("Agregar Obra");
        botonAgregarObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarObra();
            }
        });
        panelBotones.add(botonAgregarObra);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void mostrarGaleria() {
        StringBuilder contenido = new StringBuilder();
        if (galeria.getSalasGaleria().isEmpty()) {
            contenido.append("La galería no tiene salas.\n");
        } else {
            for (Sala sala : galeria.getSalasGaleria().values()) {
                contenido.append("Sala: ").append(sala.getNombre()).append("\n");
                for (Obra obra : sala.getObras()) {
                    contenido.append(" - '").append(obra.getTitulo()).append("' de '")
                            .append(obra.getArtista()).append("' (")
                            .append(obra.getYear()).append(" - $")
                            .append(obra.getPrecio()).append(")\n");
                }
                contenido.append("\n");
            }
        }
        textArea.setText(contenido.toString());
    }

    private void agregarSala() {
        String nombreSala = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva sala:");
        if (nombreSala != null && !nombreSala.trim().isEmpty()) {
            Sala nuevaSala = new Sala(nombreSala);
            galeria.agregarSala(nuevaSala);
            textArea.setText("Sala '" + nombreSala + "' agregada con éxito.");

            // Guardar la galería en el archivo CSV
            try {
                galeria.guardarEnArchivo("galeria.csv");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al guardar en el archivo CSV.");
            } 
        }
    }

    private void agregarObra() {
        String nombreSala = JOptionPane.showInputDialog(this, "Ingrese el nombre de la sala:");
        Sala sala = galeria.getSala(nombreSala);

        if (sala != null) {
            String tituloObra = JOptionPane.showInputDialog(this, "Ingrese el título de la obra:");
            String artistaObra = JOptionPane.showInputDialog(this, "Ingrese el nombre del artista:");
            String yearObra = JOptionPane.showInputDialog(this, "Ingrese el año de la obra:");
            String precioObra = JOptionPane.showInputDialog(this, "Ingrese el precio de la obra:");

            if (tituloObra != null && artistaObra != null && yearObra != null && precioObra != null) {
                Obra nuevaObra = new Obra(tituloObra, artistaObra, Integer.parseInt(yearObra), Integer.parseInt(precioObra));
                sala.agregarObra(nuevaObra);
                textArea.setText("Obra '" + tituloObra + "' agregada a la sala '" + nombreSala + "'.");

                // Guardar la galería en el archivo CSV
                try {
                    galeria.guardarEnArchivo("galeria.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar en el archivo CSV.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sala no encontrada.");
        }
    }
    
    

    public static void main(String[] args)throws IOException{
        Galeria galeria = new Galeria();
        galeria.cargarDesdeArchivo("galeria.csv");

        Sala sala1 = new Sala("Sala 1");
        sala1.agregarObra(new Obra("Pink Banana", "John Stronghead", 2000, 258000));
        sala1.agregarObra(new Obra("Charly", "Rodrigo Wolff", 2020, 259000));

        Sala sala2 = new Sala("Sala 2");
        sala2.agregarObra(new Obra("Slinky Cat", "Ernie Armstrong", 1987, 25098));
        sala2.agregarObra(new Obra("Tag Boureau", "Vicente Lennon", 1992, 259700));

        Sala sala3 = new Sala("Sala 3");
        sala3.agregarObra(new Obra("Baljeep", "Eric Sepulveda", 1886, 250898));
        sala3.agregarObra(new Obra("The green donut", "Jonathan Panther", 1925, 259260));

        galeria.agregarSala(sala1);
        galeria.agregarSala(sala2);
        galeria.agregarSala(sala3);

        GaleriaVentanaSwing ventana = new GaleriaVentanaSwing(galeria);
        ventana.setVisible(true);
    }
}
