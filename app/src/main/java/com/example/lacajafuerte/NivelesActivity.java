package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NivelesActivity extends AppCompatActivity {

    private String tipoOperacion;
    private GestorDatos gestorDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        gestorDatos = new GestorDatos(this);

        // Recibimos qué operación eligió en La Bóveda (Ej. "SUMA")
        tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");
        if (tipoOperacion == null) tipoOperacion = "SUMA"; // Por seguridad

        // Cambiamos el título para que sepa qué está jugando
        TextView tvTitulo = findViewById(R.id.tvTituloNiveles);
        tvTitulo.setText("NIVELES: " + tipoOperacion);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Arreglo con los IDs de los 10 botones
        int[] idsBotones = {
                R.id.btnLvl1, R.id.btnLvl2, R.id.btnLvl3, R.id.btnLvl4, R.id.btnLvl5,
                R.id.btnLvl6, R.id.btnLvl7, R.id.btnLvl8, R.id.btnLvl9, R.id.btnLvl10
        };

        // Obtenemos hasta qué nivel ha llegado
        int nivelMaximo = gestorDatos.getNivelMaximo(tipoOperacion);

        // Configuramos cada botón dinámicamente
        for (int i = 0; i < idsBotones.length; i++) {
            Button btnNivel = findViewById(idsBotones[i]);
            final int numeroNivel = i + 1; // Para que el nivel 0 sea el 1, etc.

            if (numeroNivel <= nivelMaximo) {
                // NIVEL DESBLOQUEADO
                btnNivel.setText(String.valueOf(numeroNivel));
                btnNivel.setEnabled(true);
                btnNivel.setAlpha(1.0f);
                btnNivel.setOnClickListener(v -> iniciarJuego(numeroNivel));
            } else {
                // NIVEL BLOQUEADO
                btnNivel.setText("🔒"); // Le ponemos un candado
                btnNivel.setEnabled(false);
                btnNivel.setAlpha(0.6f); // Lo hacemos un poco transparente
            }
        }
    }

    private void iniciarJuego(int numeroNivel) {
        // CÁLCULO DE DIFICULTAD AUTOMÁTICO
        int dificultadCalculada;
        if (numeroNivel <= 3) {
            dificultadCalculada = 1; // Niveles 1,2,3 -> Fácil
        } else if (numeroNivel <= 7) {
            dificultadCalculada = 2; // Niveles 4,5,6,7 -> Medio
        } else {
            dificultadCalculada = 3; // Niveles 8,9,10 -> Difícil
        }

        Intent intent = new Intent(NivelesActivity.this, GameActivity.class);
        intent.putExtra("TIPO_OPERACION", tipoOperacion);
        intent.putExtra("NIVEL_DIFICULTAD", dificultadCalculada); // El motor ya lo usará
        intent.putExtra("NUMERO_NIVEL", numeroNivel); // Pasamos qué nivel es para poder desbloquear el siguiente
        startActivity(intent);
    }
}