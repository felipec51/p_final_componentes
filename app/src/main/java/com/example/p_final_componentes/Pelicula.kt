package com.example.p_final_componentes

// Modelo básico para el catálogo
data class Pelicula(
    val id_pelicula: Int,
    val titulo: String,
    val anio: Int,
    val duracion_min: Int,
    val descripcion: String,
    val poster_path: String,
    val precio_alquiler: String,
    val calificacion: String,
    val director_nombre: String
)

// Modelo completo para detalles de película
data class PeliculaDetalle(
    val id_pelicula: Int,
    val titulo: String,
    val anio: Int,
    val duracion_min: Int,
    val descripcion: String,
    val poster_path: String,
    val precio_alquiler: String,
    val calificacion: String,
    val director_nombre: String,
    val numerocopias: Int,
    val estado: String,
    val actores: List<String>,
    val generos: List<String>,
    val trailers: List<String>,
    val copias_disponibles: Int
)

// Respuestas del API (opcional pero útil)
data class PeliculasResponse(
    val success: Boolean,
    val peliculas: List<Pelicula>?,
    val error: String?
)

data class PeliculaDetalleResponse(
    val success: Boolean,
    val pelicula: PeliculaDetalle?,
    val actores: List<String>?,
    val generos: List<String>?,
    val trailers: List<String>?,
    val copias_disponibles: Int?,
    val error: String?
)

// Función de extensión para convertir Pelicula a PeliculaDetalle (útil)
fun Pelicula.toDetalle(
    numerocopias: Int = 0,
    estado: String = "disponible",
    actores: List<String> = emptyList(),
    generos: List<String> = emptyList(),
    trailers: List<String> = emptyList(),
    copias_disponibles: Int = 0
): PeliculaDetalle {
    return PeliculaDetalle(
        id_pelicula = this.id_pelicula,
        titulo = this.titulo,
        anio = this.anio,
        duracion_min = this.duracion_min,
        descripcion = this.descripcion,
        poster_path = this.poster_path,
        precio_alquiler = this.precio_alquiler,
        calificacion = this.calificacion,
        director_nombre = this.director_nombre,
        numerocopias = numerocopias,
        estado = estado,
        actores = actores,
        generos = generos,
        trailers = trailers,
        copias_disponibles = copias_disponibles
    )
}