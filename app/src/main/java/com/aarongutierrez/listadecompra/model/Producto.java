package com.aarongutierrez.listadecompra.model;

public class Producto {
    private String nombre;
    private int immage;
    private Categoria categoria;

    public Producto(String nombre, int immage, Categoria categoria) {
        this.nombre = nombre;
        this.immage = immage;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }
    public int getImmage() {
        return immage;
    }

    public Categoria getCategoria() {
        return categoria;
    }

}
