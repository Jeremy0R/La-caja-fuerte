package com.example.lacajafuerte;

import android.content.Intent;
import android.media.MediaPlayer;
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
    private MediaPlayer mediaPlayerJuego;

    // Variables para el nuevo flujo de nivel
    private int preguntasContestadas = 0;
    private final int MAX_PREGUNTAS = 10;
    private int puntajeNivel = 0;
    private int numeroNivelActual = 1; // ¡NUEVA VARIABLE GLOBAL!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvRespuesta = findViewById(R.id.tvRespuesta);
        tvAcertijo = findViewById(R.id.tvAcertijo);
        progressBarJuego = findViewById(R.id.progressBarJuego);

        // ¡ENLAZAMOS EL TÍTULO DEL NIVEL!
        TextView tvNivelActual = findViewById(R.id.tvNivelActual);

        progressBarJuego.setMax(MAX_PREGUNTAS);
        progressBarJuego.setProgress(0);

        gestorDatos = new GestorDatos(this);

        if (gestorDatos.isSonidoActivado()) {
            mediaPlayerJuego = MediaPlayer.create(this, R.raw.pearl);
            if (mediaPlayerJuego != null) {
                mediaPlayerJuego.setLooping(true);
                mediaPlayerJuego.start();
            }
        }

        String tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");
        int nivelDificultad = getIntent().getIntExtra("NIVEL_DIFICULTAD", 1);

        // RECUPERAMOS EL NIVEL EXACTO EN EL QUE ESTAMOS
        numeroNivelActual = getIntent().getIntExtra("NUMERO_NIVEL", 1);

        // ACTUALIZAMOS EL TEXTO EN PANTALLA
        tvNivelActual.setText("NIVEL " + numeroNivelActual);

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

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(GameActivity.this, "Usa el botón de Pausa (||) para salir", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnPausa).setOnClickListener(v -> {
            Intent intentPausa = new Intent(GameActivity.this, PausaActivity.class);
            startActivity(intentPausa);
        });

        btnPista = findViewById(R.id.btnPista);
        btnPista.setOnClickListener(v -> {
            puntajeNivel -= 20;
            Toast.makeText(this, "-20 Puntos. ¡Aquí tienes la respuesta!", Toast.LENGTH_SHORT).show();

            int respuestaCorrectaObj = operacionActual.getRespuestaCorrecta();
            respuestaActual = String.valueOf(respuestaCorrectaObj);
            tvRespuesta.setText(respuestaActual);

            btnPista.setEnabled(false);
            btnPista.setAlpha(0.5f);
        });

        View.OnClickListener listenerNumeros = view -> {
            Button botonPresionado = (Button) view;
            if (respuestaActual.length() < 5) {
                respuestaActual += botonPresionado.getText().toString();
                tvRespuesta.setText(respuestaActual);
            }
        };

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

        findViewById(R.id.btnBorrar).setOnClickListener(v -> {
            if (!respuestaActual.isEmpty()) {
                respuestaActual = respuestaActual.substring(0, respuestaActual.length() - 1);
                tvRespuesta.setText(respuestaActual);
            }
        });

        findViewById(R.id.btnOk).setOnClickListener(v -> {
            if (respuestaActual.isEmpty()) {
                Toast.makeText(this, "Escribe una respuesta", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean esCorrecto = operacionActual.verificarRespuesta(respuestaActual);

            if (esCorrecto) {
                preguntasContestadas++;
                puntajeNivel += 10;
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

        if (btnPista != null) {
            btnPista.setEnabled(true);
            btnPista.setAlpha(1.0f);
        }
    }

    private void finalizarNivel() {
        if (mediaPlayerJuego != null) {
            mediaPlayerJuego.release();
            mediaPlayerJuego = null;
        }

        int coronasAGuardar = (puntajeNivel == 100) ? 200 : puntajeNivel;

        RecompensaCorona recompensa = new RecompensaCorona(coronasAGuardar);
        recompensa.otorgar(gestorDatos);

        String tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");

        // AHORA USAMOS LA VARIABLE GLOBAL QUE YA TENEMOS
        gestorDatos.completarNivel(tipoOperacion, numeroNivelActual);

        Intent intent = new Intent(GameActivity.this, VictoriaActivity.class);
        intent.putExtra("PUNTAJE_FINAL", puntajeNivel);
        intent.putExtra("TIPO_OPERACION", tipoOperacion);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayerJuego != null && mediaPlayerJuego.isPlaying()) {
            mediaPlayerJuego.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayerJuego != null && !mediaPlayerJuego.isPlaying() && gestorDatos.isSonidoActivado()) {
            mediaPlayerJuego.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerJuego != null) {
            mediaPlayerJuego.release();
            mediaPlayerJuego = null;
        }
    }
}