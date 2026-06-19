package com.example.lacajafuerte;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * CLASE: GestorDatos
 * PROPOSITO: Manejar la persistencia de datos de la aplicación de forma local.
 * TECNOLOGIA: Utiliza SharedPreferences para guardar datos primitivos (int, boolean, String)
 *             incluso cuando la aplicación se cierra.
 */
public class GestorDatos {

    // Constantes para identificar el archivo XML de SharedPreferences y sus claves
    private static final String PREF_NAME = "CajaFuertePrefs";
    private static final String KEY_CORONAS = "cantidad_coronas";
    private static final String KEY_SONIDO = "sonido_activado";

    // Objeto principal de SharedPreferences
    private SharedPreferences sharedPreferences;

    /**
     * CONSTRUCTOR de la clase GestorDatos
     * @param context El contexto de la Activity que está instanciando esta clase.
     *                Se usa Context.MODE_PRIVATE para que ninguna otra app acceda a estos datos.
     */
    public GestorDatos(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * METODO: agregarCoronas
     * @param cantidad Número de coronas a sumar al perfil del jugador tras ganar un nivel.
     * COMO SE USA: Instancia un SharedPreferences.Editor, modifica el valor y aplica los cambios.
     */
    public void agregarCoronas(int cantidad) {
        int coronasActuales = obtenerCoronas();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_CORONAS, coronasActuales + cantidad);
        editor.apply(); // apply() guarda de forma asíncrona (mejor rendimiento que commit)
    }

    /**
     * METODO: gastarCoronas
     * @param costo Cantidad de coronas a restar (ej. al comprar un desbloqueo en TiendaActivity).
     * @return boolean true si la compra fue exitosa, false si no le alcanzan las coronas.
     */
    public boolean gastarCoronas(int costo) {
        int coronasActuales = obtenerCoronas();
        if (coronasActuales >= costo) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_CORONAS, coronasActuales - costo);
            editor.apply();
            return true;
        }
        return false;
    }

    /**
     * METODO: obtenerCoronas
     * @return int La cantidad total de coronas guardadas. Retorna 0 si es la primera vez que juega.
     */
    public int obtenerCoronas() {
        return sharedPreferences.getInt(KEY_CORONAS, 0);
    }

    /**
     * METODO: setSonidoActivado / isSonidoActivado
     * Guarda y lee la preferencia de sonido del usuario. Por defecto es true.
     */
    public void setSonidoActivado(boolean activado) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_SONIDO, activado);
        editor.apply();
    }

    public boolean isSonidoActivado() {
        return sharedPreferences.getBoolean(KEY_SONIDO, true);
    }

    /**
     * METODO: reiniciarProgreso
     * Borra todas las coronas acumuladas.
     */
    // Busca este método en tu GestorDatos.java y reemplázalo completo
    // ====================================================================
    // REINICIAR PROGRESO (Versión limpia y profesional a prueba de bugs)
    // ====================================================================
    public void reiniciarProgreso() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear(); // ¡LA SOLUCIÓN MAESTRA! Borra absolutamente todo el contenido del archivo

        // Guardamos los cambios de forma inmediata en la memoria física
        editor.apply();
    }

    // Método para desbloquear (guardar en memoria)
    public void desbloquearOperacion(String operacion) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("desbloqueado_" + operacion, true);
        editor.apply();
    }

    // Método para preguntar si ya está desbloqueado
    public boolean isOperacionDesbloqueada(String operacion) {
        // La suma siempre es true por defecto. Las demás son false.
        if (operacion.equals("SUMA")) return true;
        return sharedPreferences.getBoolean("desbloqueado_" + operacion, false);
    }

    // Obtiene en qué nivel va el jugador (por defecto empieza en el 1)
    public int getNivelMaximo(String operacion) {
        return sharedPreferences.getInt("nivel_maximo_" + operacion, 1);
    }

    // Desbloquea el siguiente nivel cuando gana una partida
    public void completarNivel(String operacion, int nivelJugado) {
        int nivelActual = getNivelMaximo(operacion);
        // Si acaba de pasar el nivel más alto que tenía, y no ha llegado al 10, le abrimos el siguiente
        if (nivelJugado >= nivelActual && nivelJugado < 10) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nivel_maximo_" + operacion, nivelJugado + 1);
            editor.apply();
        }
    }
}