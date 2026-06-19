package com.example.lacajafuerte;

import java.util.Random;

public class Suma extends OperacionMatematica {

    public Suma(int nivelDificultad) {
        super(nivelDificultad);
    }

    @Override
    public void generarOperacion() {
        Random random = new Random();

        // 1. Generamos el PRIMER número basándonos en la dificultad
        if (nivelDificultad == 1) {
            num1 = random.nextInt(9) + 1;       // 1 al 9
        } else if (nivelDificultad == 2) {
            num1 = random.nextInt(90) + 10;     // 10 al 99
        } else {
            // EL FIX: 3 cifras (100 al 999)
            num1 = random.nextInt(900) + 100;
        }

        // 2. Generamos el SEGUNDO número usando la misma lógica
        if (nivelDificultad == 1) {
            num2 = random.nextInt(9) + 1;
        } else if (nivelDificultad == 2) {
            num2 = random.nextInt(90) + 10;
        } else {
            num2 = random.nextInt(900) + 100;
        }

        // 3. Calculamos la respuesta
        respuestaCorrecta = num1 + num2;
    }

    @Override
    public String getAcertijoComoTexto() {
        return num1 + " + " + num2 + " = ?";
    }
}