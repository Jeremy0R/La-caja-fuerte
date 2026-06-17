package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView tvRespuesta;
    private TextView tvAcertijo;
    private ProgressBar progressBarJuego;
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

        // Agrega esta línea para recibir la dificultad (por defecto 1)
        int nivelDificultad = getIntent().getIntExtra("NIVEL_DIFICULTAD", 1);

        if ("RESTA".equals(tipoOperacion)) {
            operacionActual = new Resta(nivelDificultad); // Le pasamos la variable
        } else if ("MULT".equals(tipoOperacion)) {
            operacionActual = new Multiplicacion(nivelDificultad);
        } else if ("DIV".equals(tipoOperacion)) {
            operacionActual = new Division(nivelDificultad);
        } else {
            operacionActual = new Suma(nivelDificultad);
        }

        generarNuevoAcertijo();

        // Lógica del botón de Pausa (Tu ImageButton con el id btnPausa)
        findViewById(R.id.btnPausa).setOnClickListener(v -> {
            Intent intentPausa = new Intent(GameActivity.this, PausaActivity.class);
            startActivity(intentPausa);
        });

        // (Opcional) De una vez puedes enlazar tu botón de pista a la Ayuda
        findViewById(R.id.btnPista).setOnClickListener(v -> {
            Intent intentAyuda = new Intent(GameActivity.this, AyudaActivity.class);
            startActivity(intentAyuda);
        });

        // 1. Listener de los botones numéricos (Se mantiene igual)
        View.OnClickListener listenerNumeros = view -> {
            Button botonPresionado = (Button) view;
            if (respuestaActual.length() < 5) {
                respuestaActual += botonPresionado.getText().toString();
                tvRespuesta.setText(respuestaActual);
            }
        };

        // Asignamos el listener a los 10 botones
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

        // 2. Lógica del botón DEL
        findViewById(R.id.btnBorrar).setOnClickListener(v -> {
            if (!respuestaActual.isEmpty()) {
                respuestaActual = respuestaActual.substring(0, respuestaActual.length() - 1);
                tvRespuesta.setText(respuestaActual);
            }
        });

        // 3. Lógica del botón OK (¡Actualizado con el progreso!)
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

                // Verificamos si ya terminó el nivel
                if (preguntasContestadas >= MAX_PREGUNTAS) {
                    finalizarNivel();
                } else {
                    Toast.makeText(this, "¡Bien! Siguiente...", Toast.LENGTH_SHORT).show();
                    generarNuevoAcertijo();
                }
            } else {
                Toast.makeText(this, "Inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                puntajeNivel -= 2; // Castigo por error (opcional)
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
    }

    //Método nuevo para manejar la victoria
    private void finalizarNivel() {
        // Otorgamos la recompensa global (ej. 1 corona por pasar el nivel)
        gestorDatos.agregarCoronas(1);

        // Pasamos a la nueva pantalla de Victoria (¡Una Activity más para la rúbrica!)
        Intent intent = new Intent(GameActivity.this, VictoriaActivity.class);
        intent.putExtra("PUNTAJE_FINAL", puntajeNivel);
        startActivity(intent);

        // Cerramos GameActivity para que si el niño presiona "Atrás", no vuelva al juego terminado
        finish();
    }
}