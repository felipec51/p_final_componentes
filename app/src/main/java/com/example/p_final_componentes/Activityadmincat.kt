package com.example.p_final_componentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme


class Activityadmincat : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PantallaCompleta2()
            }
        }
    }
}

@Composable
fun PantallaCompleta2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // 👉 PRIMERA PANTALLA (Admin)
        App()

        // 👉 SEGUNDA PANTALLA (Catálogo de Películas)
        catalogopeli()
    }
}
