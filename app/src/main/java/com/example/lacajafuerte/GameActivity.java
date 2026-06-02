package com.example.lacajafuerte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Al estar en el mismo paquete, no hace falta importar OperacionMatematica, Suma ni GestorDatos.


public class GameActivity extends AppCompatActivity {

    private TextView tvRespuesta;
    private TextView tvAcertijo;
    private String respuestaActual = "";

    // Variables para nuestra lógica
    private OperacionMatematica operacionActual;
    private GestorDatos gestorDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvRespuesta = findViewById(R.id.tvRespuesta);
        tvAcertijo = findViewById(R.id.tvAcertijo);

        // Inicializamos el gestor para guardar el puntaje
        gestorDatos = new GestorDatos(this);

        // Identificamos qué operación eligió el usuario
        String tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");

        if ("RESTA".equals(tipoOperacion)) {
            operacionActual = new Resta(1);
        } else if ("MULT".equals(tipoOperacion)) {
            operacionActual = new Multiplicacion(1);
        } else if ("DIV".equals(tipoOperacion)) {
            operacionActual = new Division(1);
        } else {
            operacionActual = new Suma(1); // Por defecto Suma
        }

        // Generamos el primer acertijo al entrar a la pantalla
        generarNuevoAcertijo();

        // 1. Listener de los botones numéricos
        View.OnClickListener listenerNumeros = view -> {
            Button botonPresionado = (Button) view;
            if (respuestaActual.length() < 5) {
                respuestaActual += botonPresionado.getText().toString();
                tvRespuesta.setText(respuestaActual);
            }
        };

        // Asignamos el listener a los 10 botones (asegúrate de que los IDs coincidan con tu XML)
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

        // 2. Lógica del botón DEL (Borrar)
        findViewById(R.id.btnBorrar).setOnClickListener(v -> {
            if (!respuestaActual.isEmpty()) {
                respuestaActual = respuestaActual.substring(0, respuestaActual.length() - 1);
                tvRespuesta.setText(respuestaActual);
            }
        });

        // 3. Lógica del botón OK (¡Aquí ocurre la magia!)
        findViewById(R.id.btnOk).setOnClickListener(v -> {
            if (respuestaActual.isEmpty()) {
                Toast.makeText(this, "Escribe una respuesta", Toast.LENGTH_SHORT).show();
                return;
            }

            // Usamos la sobrecarga de métodos que creaste en la clase padre
            boolean esCorrecto = operacionActual.verificarRespuesta(respuestaActual);

            if (esCorrecto) {
                Toast.makeText(this, "¡CORRECTO! +1 Corona", Toast.LENGTH_SHORT).show();
                gestorDatos.agregarCoronas(1); // Guardamos el progreso (Persistencia)
                generarNuevoAcertijo(); // Pasamos al siguiente reto
            } else {
                Toast.makeText(this, "Inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                // Limpiamos el texto para que lo vuelva a intentar
                respuestaActual = "";
                tvRespuesta.setText(respuestaActual);
            }
        });
    }

    // Método para encapsular la generación de un nuevo reto
    private void generarNuevoAcertijo() {
        operacionActual.generarOperacion();
        tvAcertijo.setText(operacionActual.getAcertijoComoTexto());
        // Limpiamos la pantalla negra para la nueva respuesta
        respuestaActual = "";
        tvRespuesta.setText(respuestaActual);
    }
}