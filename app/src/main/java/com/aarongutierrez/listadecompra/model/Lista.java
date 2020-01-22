package com.aarongutierrez.listadecompra.model;

import java.util.ArrayList;

public class Lista {
    private String nomLista;
    private String timestamp;


    private ArrayList<Producto> productos;

    public Lista(String nomLista, String timestamp) {
        this.nomLista = nomLista;
        this.timestamp = timestamp;
        this.productos = productos;
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public String getNomLista() {
        return nomLista;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
