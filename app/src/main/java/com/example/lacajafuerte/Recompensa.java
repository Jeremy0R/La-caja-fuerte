package com.example.lacajafuerte;

/**
 * CLASE BASE (Superclase)
 */
public class Recompensa {
    protected int cantidad;

    // Constructor base
    public Recompensa(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método que será sobrecargado y sobrescrito
    public void otorgar(GestorDatos gestor) {
        gestor.agregarCoronas(this.cantidad);
    }
}