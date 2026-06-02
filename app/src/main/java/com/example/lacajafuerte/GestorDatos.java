package com.example.lacajafuerte;

import android.content.Context;
import android.content.SharedPreferences;

public class GestorDatos {
    private static final String NOMBRE_ARCHIVO = "CajaFuertePrefs";
    private static final String KEY_CORONAS = "totalCoronas";
    private SharedPreferences preferencias;

    // Constructor que recibe el contexto de la Activity actual
    public GestorDatos(Context context) {
        preferencias = context.getSharedPreferences(NOMBRE_ARCHIVO, Context.MODE_PRIVATE);
    }

    // Método para sumar puntos al ganar
    public void agregarCoronas(int cantidad) {
        int coronasActuales = obtenerCoronas();
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(KEY_CORONAS, coronasActuales + cantidad);
        editor.apply(); // .apply() guarda en segundo plano sin trabar el juego
    }

    // Método para leer los puntos al cargar el menú
    public int obtenerCoronas() {
        return preferencias.getInt(KEY_CORONAS, 0); // 0 es el valor por defecto si es la primera vez
    }
}