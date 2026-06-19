package com.example.lacajafuerte;

import java.util.Random;

public class Resta extends OperacionMatematica {

    public Resta(int nivelDificultad) {
        super(nivelDificultad);
    }

    @Override
    public void generarOperacion() {
        Random random = new Random();
        int temp1, temp2;

        // Generamos los dos números según la dificultad (1, 2 o 3 cifras)
        if (nivelDificultad == 1) {
            temp1 = random.nextInt(9) + 1;
            temp2 = random.nextInt(9) + 1;
        } else if (nivelDificultad == 2) {
            temp1 = random.nextInt(90) + 10;
            temp2 = random.nextInt(90) + 10;
        } else {
            temp1 = random.nextInt(900) + 100;
            temp2 = random.nextInt(900) + 100;
        }

        // El mayor siempre va primero para no tener resultados negativos
        num1 = Math.max(temp1, temp2);
        num2 = Math.min(temp1, temp2);

        respuestaCorrecta = num1 - num2;
    }

    @Override
    public String getAcertijoComoTexto() {
        return num1 + " - " + num2 + " = ?";
    }
}