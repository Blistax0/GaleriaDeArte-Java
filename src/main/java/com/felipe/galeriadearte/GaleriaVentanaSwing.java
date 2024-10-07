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
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        // Botón para mostrar galería
        JButton botonMostrarGaleria = new JButton("Mostrar Galería");
        botonMostrarGaleria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGaleria();
            }
        });
        panelBotones.add(botonMostrarGaleria);

        // Botón para agregar sala
        JButton botonAgregarSala = new JButton("Agregar Sala");
        botonAgregarSala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarSala();
            }
        });
        panelBotones.add(botonAgregarSala);

        // Botón para agregar obra
        JButton botonAgregarObra = new JButton("Agregar Obra");
        botonAgregarObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarObra();
            }
        });
        panelBotones.add(botonAgregarObra);

        // Botón para filtrar obras
        JButton botonFiltrar = new JButton("Filtrar Obras");
        botonFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarObras();
            }
        });
        panelBotones.add(botonFiltrar);

        // Botón para eliminar obra
        JButton botonEliminarObra = new JButton("Eliminar Obra");
        botonEliminarObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarObra();
            }
        });
        panelBotones.add(botonEliminarObra);

        // Menú de edición
        JMenuBar menuBar = new JMenuBar();
        JMenu menuEdicion = new JMenu("Edición");
        JMenuItem itemEditarObra = new JMenuItem("Editar Obra");

        itemEditarObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarObra();
            }
        });

        menuEdicion.add(itemEditarObra);
        menuBar.add(menuEdicion);
        setJMenuBar(menuBar);

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

    private void editarObra() {
        String nombreSala = JOptionPane.showInputDialog(this, "Ingrese el nombre de la sala:");
        Sala sala = galeria.getSala(nombreSala);

        if (sala != null) {
            String tituloObra = JOptionPane.showInputDialog(this, "Ingrese el título de la obra a editar:");
            Obra obra = sala.getObra(tituloObra);
            if (obra != null) {
                String nuevoTitulo = JOptionPane.showInputDialog(this, "Ingrese el nuevo título:", obra.getTitulo());
                String nuevoArtista = JOptionPane.showInputDialog(this, "Ingrese el nuevo artista:", obra.getArtista());
                String nuevoYear = JOptionPane.showInputDialog(this, "Ingrese el nuevo año:", obra.getYear());
                String nuevoPrecio = JOptionPane.showInputDialog(this, "Ingrese el nuevo precio:", obra.getPrecio());

                if (nuevoTitulo != null && nuevoArtista != null && nuevoYear != null && nuevoPrecio != null) {
                    obra.setTitulo(nuevoTitulo);
                    obra.setArtista(nuevoArtista);
                    obra.setYear(Integer.parseInt(nuevoYear));
                    obra.setPrecio(Integer.parseInt(nuevoPrecio));
                    textArea.setText("Obra '" + tituloObra + "' editada con éxito.");
                    try {
                        galeria.guardarEnArchivo("galeria.csv");
                    } catch (IOException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error al guardar en el archivo CSV.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Obra no encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sala no encontrada.");
        }
    }

    private void eliminarObra() {
        String nombreSala = JOptionPane.showInputDialog(this, "Ingrese el nombre de la sala:");
        Sala sala = galeria.getSala(nombreSala);

        if (sala != null) {
            String tituloObra = JOptionPane.showInputDialog(this, "Ingrese el título de la obra a eliminar:");
            Obra obra = sala.getObra(tituloObra);
            if (obra != null) {
                sala.eliminarObra(obra);
                try {
                    galeria.guardarEnArchivo("galeria.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar en el archivo CSV.");
                }
                textArea.setText("Obra '" + tituloObra + "' eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Obra no encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sala no encontrada.");
        }
    }

    private void filtrarObras() {
        String criterio = JOptionPane.showInputDialog(this, "Ingrese el criterio para filtrar (artista, año o precio):");
        String valor = JOptionPane.showInputDialog(this, "Ingrese el valor de filtrado:");

        StringBuilder contenido = new StringBuilder();
        for (Sala sala : galeria.getSalasGaleria().values()) {
            contenido.append("Sala: ").append(sala.getNombre()).append("\n");
            for (Obra obra : sala.getObras()) {
                boolean coincide = false;
                switch (criterio.toLowerCase()) {
                    case "artista":
                        coincide = obra.getArtista().equalsIgnoreCase(valor);
                        break;
                    case "año":
                        coincide = Integer.toString(obra.getYear()).equals(valor);
                        break;
                    case "precio":
                        coincide = Integer.toString(obra.getPrecio()).equals(valor);
                        break;
                }
                if (coincide) {
                    contenido.append(" - '").append(obra.getTitulo()).append("' de '")
                            .append(obra.getArtista()).append("' (")
                            .append(obra.getYear()).append(" - $")
                            .append(obra.getPrecio()).append(")\n");
                }
            }
            contenido.append("\n");
        }
        textArea.setText(contenido.toString());
    }

    public static void main(String[] args) throws IOException {
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
