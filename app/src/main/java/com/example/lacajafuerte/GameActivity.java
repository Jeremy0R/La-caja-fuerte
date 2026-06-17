package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;

    public class GameActivity extends AppCompatActivity {

        private TextView tvRespuesta;
        private TextView tvAcertijo;
        private ProgressBar progressBarJuego;
        private ImageButton btnPista;
        private String respuestaActual = "";

    // Variables de lógica
    private OperacionMatematica operacionActual;
    private GestorDatos gestorDatos;

    // Variables para el nuevo flujo de nivel
    private int preguntasContestadas = 0;
    private final int MAX_PREGUNTAS = 10;
    private int puntajeNivel = 0; // Para sumar puntos o restarlos si usan ayudas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvRespuesta = findViewById(R.id.tvRespuesta);
        tvAcertijo = findViewById(R.id.tvAcertijo);
        progressBarJuego = findViewById(R.id.progressBarJuego);

        // Configuramos la barra de progreso
        progressBarJuego.setMax(MAX_PREGUNTAS);
        progressBarJuego.setProgress(0);

        gestorDatos = new GestorDatos(this);

        String tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");
        int nivelDificultad = getIntent().getIntExtra("NIVEL_DIFICULTAD", 1);

        if ("RESTA".equals(tipoOperacion)) {
            operacionActual = new Resta(nivelDificultad);
        } else if ("MULT".equals(tipoOperacion)) {
            operacionActual = new Multiplicacion(nivelDificultad);
        } else if ("DIV".equals(tipoOperacion)) {
            operacionActual = new Division(nivelDificultad);
        } else {
            operacionActual = new Suma(nivelDificultad);
        }

        generarNuevoAcertijo();

        // ====================================================================
        // BLOQUEADOR DE GESTOS LATERALES Y BOTÓN ATRÁS (Mecanismo AndroidX)
        // ====================================================================
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Intercepta tanto el botón físico como el deslizamiento lateral de gestos
                Toast.makeText(GameActivity.this, "Usa el botón de Pausa (||) para salir", Toast.LENGTH_SHORT).show();
            }
        });

        // Lógica del botón de Pausa (||)
        findViewById(R.id.btnPausa).setOnClickListener(v -> {
            Intent intentPausa = new Intent(GameActivity.this, PausaActivity.class);
            startActivity(intentPausa);
        });

        // Lógica del botón del Foquito (Pista) - Castiga con 20 puntos
        // Inicializamos el botón de la pista
        btnPista = findViewById(R.id.btnPista);

        // Lógica del botón del Foquito (Pista)
        btnPista.setOnClickListener(v -> {
            puntajeNivel -= 20;
            Toast.makeText(this, "-20 Puntos. ¡Aquí tienes la respuesta!", Toast.LENGTH_SHORT).show();

            int respuestaCorrectaObj = operacionActual.getRespuestaCorrecta();
            respuestaActual = String.valueOf(respuestaCorrectaObj);
            tvRespuesta.setText(respuestaActual);

            // ¡MAGIA UX! Desactivamos el botón para que no lo puedan presionar dos veces
            btnPista.setEnabled(false);
            btnPista.setAlpha(0.5f); // Lo hacemos semitransparente para indicar que está apagado
        });

        // 1. Listener de los botones numéricos
        View.OnClickListener listenerNumeros = view -> {
            Button botonPresionado = (Button) view;
            if (respuestaActual.length() < 5) {
                respuestaActual += botonPresionado.getText().toString();
                tvRespuesta.setText(respuestaActual);
            }
        };

        // Asignamos el listener a los 10 botones numéricos
        findViewById(R.id.btnNum0).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum1).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum2).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum3).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum4).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum5).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum6).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum7).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum8).setOnClickListener(listenerNumeros);
        findViewById(R.id.btnNum9).setOnClickListener(listenerNumeros);

        // 2. Lógica del botón DEL (Borrar) - ¡RESTAURADO!
        findViewById(R.id.btnBorrar).setOnClickListener(v -> {
            if (!respuestaActual.isEmpty()) {
                respuestaActual = respuestaActual.substring(0, respuestaActual.length() - 1);
                tvRespuesta.setText(respuestaActual);
            }
        });

        // 3. Lógica del botón OK
        findViewById(R.id.btnOk).setOnClickListener(v -> {
            if (respuestaActual.isEmpty()) {
                Toast.makeText(this, "Escribe una respuesta", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean esCorrecto = operacionActual.verificarRespuesta(respuestaActual);

            if (esCorrecto) {
                preguntasContestadas++;
                puntajeNivel += 10; // 10 puntos por acierto limpio
                progressBarJuego.setProgress(preguntasContestadas);

                if (preguntasContestadas >= MAX_PREGUNTAS) {
                    finalizarNivel();
                } else {
                    Toast.makeText(this, "¡Bien! Siguiente...", Toast.LENGTH_SHORT).show();
                    generarNuevoAcertijo();
                }
            } else {
                Toast.makeText(this, "Inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                puntajeNivel -= 2;
                respuestaActual = "";
                tvRespuesta.setText(respuestaActual);
            }
        });
    }

        private void generarNuevoAcertijo() {
            operacionActual.generarOperacion();
            tvAcertijo.setText(operacionActual.getAcertijoComoTexto());
            respuestaActual = "";
            tvRespuesta.setText(respuestaActual);

            // Reactivamos el foquito de pista para la nueva pregunta
            if (btnPista != null) {
                btnPista.setEnabled(true);
                btnPista.setAlpha(1.0f); // Le regresamos su color original
            }
        }

    private void finalizarNivel() {
        RecompensaCorona recompensa = new RecompensaCorona();

        if (puntajeNivel == 100) {
            recompensa.otorgar(gestorDatos, 2);
        } else {
            recompensa.otorgar(gestorDatos);
        }

        Intent intent = new Intent(GameActivity.this, VictoriaActivity.class);
        intent.putExtra("PUNTAJE_FINAL", puntajeNivel);
        startActivity(intent);
        finish();
    }
}