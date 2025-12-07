package com.example.p_final_componentes;

import java.io.Serializable;

public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id_actor;
    private String nombre;

    // Constructor vacío
    public Actor() {
    }

    // Constructor con parámetros
    public Actor(int id_actor, String nombre) {
        this.id_actor = id_actor;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id_actor=" + id_actor +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}