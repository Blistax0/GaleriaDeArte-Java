package com.felipe.galeriadearte;

class ObraEscultura extends Obra {
    private String material;

    public ObraEscultura(String titulo, String artista, int year, int precio, String material) {
        super(titulo, artista, year, precio);
        this.material = material;
    }

    @Override
    public String getDetalles() {
        return super.getDetalles() + " - Material: " + material;
    }
}
