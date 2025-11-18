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
    private int copias_totales;
    private String generos_detalle; // Para la lista de géneros concatenada
    private String elenco;
    private String director_nombre;
    // 2. Constructor Vacío
    private int id_trailer;
    private String  url_trailer;
    private String titulo_trailer;
    public Pelicula() {
    }

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

    public void setcopiasTotales(int copias_totales){
        this.copias_totales=copias_totales;
    }
    public int getcopiasTotales(){
        return copias_totales;
    }
    // 3. Getters y Setters (Añadir a la sección 4.)
    public String getGeneros_detalle() {
        return generos_detalle;
    }

    public void setGeneros_detalle(String generos_detalle) {
        this.generos_detalle = generos_detalle;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }
    public String getDirector_nombre() {
        return director_nombre;
    }
    public int getid_trailer() {
        return id_trailer;
    }
    public String getUrl_trailer() {
        return url_trailer;
    }
    public String getTitulo_trailer() {
        return titulo_trailer;
    }


    public void setDirector_nombre(String director_nombre) {
        this.director_nombre = director_nombre;
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