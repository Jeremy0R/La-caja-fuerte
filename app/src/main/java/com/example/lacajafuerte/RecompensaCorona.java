package com.example.lacajafuerte;

/**
 * CLASE HIJA (Demuestra Herencia, Sobrecarga de Constructores y Métodos)
 */
public class RecompensaCorona extends Recompensa {

    // 1. SOBRECARGA DE CONSTRUCTORES
    // Constructor por defecto (Te da 100 coronas como pediste)
    public RecompensaCorona() {
        super(100);
    }

    // Constructor sobrecargado (Por si en el nivel difícil quieres darle más)
    public RecompensaCorona(int cantidadEspecial) {
        super(cantidadEspecial);
    }

    // 2. SOBRECARGA DE MÉTODOS (Overloading)
    // Otorgar normal (heredado)
    @Override
    public void otorgar(GestorDatos gestor) {
        super.otorgar(gestor);
    }

    // Otorgar sobrecargado con un multiplicador (Ej. x2 por no usar ayudas)
    public void otorgar(GestorDatos gestor, int multiplicador) {
        gestor.agregarCoronas(this.cantidad * multiplicador);
    }
}