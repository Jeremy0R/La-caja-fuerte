package com.example.lacajafuerte;

import java.util.Random;

public class Division extends OperacionMatematica {

    public Division(int nivelDificultad) {
        super(nivelDificultad);
    }

    @Override
    public void generarOperacion() {
        Random random = new Random();
        int divisor, cociente;

        // Generamos el divisor y la respuesta (cociente) primero
        if (nivelDificultad == 1) {
            divisor = random.nextInt(9) + 1;
            cociente = random.nextInt(9) + 1;
        } else if (nivelDificultad == 2) {
            divisor = random.nextInt(90) + 10;
            cociente = random.nextInt(90) + 10;
        } else {
            divisor = random.nextInt(900) + 100;
            cociente = random.nextInt(900) + 100;
        }

        num2 = divisor;
        respuestaCorrecta = cociente;

        // Multiplicamos para obtener el dividendo, garantizando que la división sea perfecta
        num1 = num2 * respuestaCorrecta;
    }

    @Override
    public String getAcertijoComoTexto() {
        return num1 + " ÷ " + num2 + " = ?";
    }
}