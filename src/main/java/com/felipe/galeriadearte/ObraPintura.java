package com.felipe.galeriadearte;

class ObraPintura extends Obra {
    private String estilo;

    public ObraPintura(String titulo, String artista, int year, int precio, String estilo) {
        super(titulo, artista, year, precio);
        this.estilo = estilo;
    }

    @Override
    public String getDetalles() {
        return super.getDetalles() + " - Estilo: " + estilo;
    }
}
