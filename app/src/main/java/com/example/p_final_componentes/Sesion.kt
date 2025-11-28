package com.example.p_final_componentes

object Sesion {
    var usuarioActual: Usuario? = null

    fun cerrarSesion() {
        usuarioActual = null
    }

    fun estaLogueado(): Boolean {
        return usuarioActual != null
    }
}