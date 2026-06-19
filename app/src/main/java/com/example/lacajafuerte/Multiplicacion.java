package com.example.lacajafuerte;

import java.util.Random;

public class Multiplicacion extends OperacionMatematica {

    public Multiplicacion(int nivelDificultad) {
        super(nivelDificultad);
    }

    @Override
    public void generarOperacion() {
        Random random = new Random();

        if (nivelDificultad == 1) {
            num1 = random.nextInt(9) + 1;
            num2 = random.nextInt(9) + 1;
        } else if (nivelDificultad == 2) {
            num1 = random.nextInt(90) + 10;
            num2 = random.nextInt(90) + 10;
        } else {
            num1 = random.nextInt(900) + 100;
            num2 = random.nextInt(900) + 100;
        }

        // Ordenamos para que el número más grande aparezca primero (es más fácil de leer)
        int mayor = Math.max(num1, num2);
        int menor = Math.min(num1, num2);
        num1 = mayor;
        num2 = menor;

        respuestaCorrecta = num1 * num2;
    }

    @Override
    public String getAcertijoComoTexto() {
        return num1 + " x " + num2 + " = ?";
    }
}