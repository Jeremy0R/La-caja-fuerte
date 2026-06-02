package com.example.lacajafuerte;

import java.util.Random;

// REQUISITO: Herencia de al menos un nivel (extends)
public class Suma extends OperacionMatematica {

    // Usamos el constructor de la clase padre
    public Suma(int nivelDificultad) {
        super(nivelDificultad);
    }

    // REQUISITO: Sobrescritura de métodos
    @Override
    public void generarOperacion() {
        Random random = new Random();
        // Genera números dependiendo del nivel (Nivel 1: hasta 10, Nivel 2: hasta 20...)
        int limite = nivelDificultad * 10;

        num1 = random.nextInt(limite) + 1;
        num2 = random.nextInt(limite) + 1;
        respuestaCorrecta = num1 + num2;
    }

    @Override
    public String getAcertijoComoTexto() {
        return num1 + " + " + num2 + " = ?";
    }
}