package com.example.p_final_componentes;

import java.io.Serializable;

public class ItemReporte implements Serializable {

    private int id;
    private String nombreUsuario;
    private String tituloPerlicula;
    private String fechaAlquiler;
    private String fechaDevolucion;
    private double precioAlquiler;
    private String estadoAlquiler;
    private int cantidadAlquileres;

    // Constructor vacío
    public ItemReporte() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloPerlicula() {
        return tituloPerlicula;
    }

    public void setTituloPerlicula(String tituloPerlicula) {
        this.tituloPerlicula = tituloPerlicula;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(String fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public String getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public void setEstadoAlquiler(String estadoAlquiler) {
        this.estadoAlquiler = estadoAlquiler;
    }

    public int getCantidadAlquileres() {
        return cantidadAlquileres;
    }

    public void setCantidadAlquileres(int cantidadAlquileres) {
        this.cantidadAlquileres = cantidadAlquileres;
    }
}