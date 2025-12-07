package com.example.p_final_componentes;

import java.io.Serializable;

public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id_genero;
    private String nombre;

    // Constructor vacío
    public Genero() {
    }

    // Constructor con parámetros
    public Genero(int id_genero, String nombre) {
        this.id_genero = id_genero;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id_genero=" + id_genero +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}