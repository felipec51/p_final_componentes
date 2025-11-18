package com.example.p_final_componentes;
import java.io.Serializable;

public class Pelicula implements Serializable {

    // 1. Atributos (Campos de la tabla pelicula)
    private int id_pelicula;
    private String titulo;
    private String descripcion;
    private int anio_lanzamiento;
    private int duracion; // En minutos
    private String clasificacion;
    private String idioma;
    private String imagen_url;
    // Opcional: private String generos; // Si decides incluirlo en la consulta PHP
    private double precio_alquiler;
    private int copias_disponibles;
    // 2. Constructor Vacío
    public Pelicula() {
    }

    // 3. Constructor Completo
    public Pelicula(int id_pelicula, String titulo, String descripcion, int anio_lanzamiento, int duracion, String clasificacion, String idioma, String imagen_url, double precio_alquiler, int copias_disponibles) {
        this.id_pelicula = id_pelicula;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.anio_lanzamiento = anio_lanzamiento;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.idioma = idioma;
        this.imagen_url = imagen_url;
        this.precio_alquiler = precio_alquiler; // <--- Inicialización
        this.copias_disponibles = copias_disponibles; // <--- Inicialización
    }

    // 4. Getters y Setters
    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnio_lanzamiento() {
        return anio_lanzamiento;
    }

    public void setAnio_lanzamiento(int anio_lanzamiento) {
        this.anio_lanzamiento = anio_lanzamiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public double getPrecio_alquiler() {
        return precio_alquiler;
    }

    public void setPrecio_alquiler(double precio_alquiler) {
        this.precio_alquiler = precio_alquiler;
    }

    public int getCopias_disponibles() {
        return copias_disponibles;
    }

    public void setCopias_disponibles(int copias_disponibles) {
        this.copias_disponibles = copias_disponibles;
    }
    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id_pelicula +
                ", titulo='" + titulo + '\'' +
                ", anio=" + anio_lanzamiento +
                '}';
    }
}