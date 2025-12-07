package com.example.p_final_componentes;

import java.io.Serializable;


public class Director implements Serializable {
    private int id_director;
    private String nombre;

    public Director() {
    }

    public Director(int id_director, String nombre) {
        this.id_director = id_director;
        this.nombre = nombre;
    }

    public int getId_director() {
        return id_director;
    }

    public void setId_director(int id_director) {
        this.id_director = id_director;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id_director +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}