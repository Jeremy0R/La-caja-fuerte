package com.example.lacajafuerte;

import java.util.Random;

public class Division extends OperacionMatematica {

    public Division(int nivelDificultad) {
        super(nivelDificultad);
    }

    @Override
    public void generarOperacion() {
        Random random = new Random();
        int limite = nivelDificultad * 10;

        int temp1 = random.nextInt(limite) + 1;
        int temp2 = random.nextInt(limite) + 1;

        // El mayor siempre va primero para no tener resultados negativos
        num1 = Math.max(temp1, temp2);
        num2 = Math.min(temp1, temp2);

        respuestaCorrecta = num1 / num2;
    }

    @Override
    public String getAcertijoComoTexto() {
        return num1 + " ÷ " + num2 + " = ?";
    }
}