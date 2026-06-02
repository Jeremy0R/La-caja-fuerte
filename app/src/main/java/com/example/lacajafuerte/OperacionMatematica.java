package com.example.lacajafuerte;
public abstract class OperacionMatematica {
    protected int num1;
    protected int num2;
    protected int respuestaCorrecta;
    protected int nivelDificultad;

    // ==========================================
    // REQUISITO: Sobrecarga de Constructores
    // ==========================================

    // Constructor 1: Por defecto (Nivel 1)
    public OperacionMatematica() {
        this.nivelDificultad = 1;
    }

    // Constructor 2: Pasándole un nivel específico
    public OperacionMatematica(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    // Método abstracto que las hijas (Suma, Resta) están obligadas a programar
    public abstract void generarOperacion();

    public abstract String getAcertijoComoTexto();

    // ==========================================
    // REQUISITO: Sobrecarga de Métodos
    // ==========================================

    // Método 1: Verifica si le pasas la respuesta como número entero (int)
    public boolean verificarRespuesta(int respuestaUsuario) {
        return respuestaUsuario == respuestaCorrecta;
    }

    // Método 2: Verifica si le pasas la respuesta como texto (String) desde el teclado
    public boolean verificarRespuesta(String respuestaUsuario) {
        try {
            int respuesta = Integer.parseInt(respuestaUsuario);
            return respuesta == respuestaCorrecta;
        } catch (NumberFormatException e) {
            return false; // Si por algún error llega texto vacío o raro, no truena la app
        }
    }
}