package com.example.lacajafuerte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    // Variables globales de la pantalla
    private TextView tvRespuesta;
    private String respuestaActual = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Enlazamos la pantalla negra donde se escriben los números
        tvRespuesta = findViewById(R.id.tvRespuesta);

        // 1. Creamos un Listener (escuchador) común para todos los botones numéricos
        View.OnClickListener listenerNumeros = view -> {
            Button botonPresionado = (Button) view;

            // Limitamos a 5 dígitos para que no escriban números infinitos y rompan el diseño
            if (respuestaActual.length() < 5) {
                respuestaActual += botonPresionado.getText().toString();
                tvRespuesta.setText(respuestaActual);
            }
        };

        // 2. Le asignamos ese mismo Listener a los 10 botones numéricos
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

        // 3. Lógica del botón DEL (Borrar el último número)
        findViewById(R.id.btnBorrar).setOnClickListener(v -> {
            if (!respuestaActual.isEmpty()) {
                // Cortamos el último carácter del texto
                respuestaActual = respuestaActual.substring(0, respuestaActual.length() - 1);
                tvRespuesta.setText(respuestaActual);
            }
        });

        // 4. Lógica del botón OK (Confirmar respuesta)
        findViewById(R.id.btnOk).setOnClickListener(v -> {
            if (respuestaActual.isEmpty()) {
                Toast.makeText(this, "¡Escribe una respuesta primero!", Toast.LENGTH_SHORT).show();
            } else {
                // Por ahora solo mostraremos un mensajito, luego lo conectaremos con la validación matemática
                Toast.makeText(this, "Verificando respuesta: " + respuestaActual, Toast.LENGTH_SHORT).show();
            }
        });
    }
}