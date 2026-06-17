package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DificultadActivity extends AppCompatActivity {

    private String tipoOperacion = "SUMA"; // Valor por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificultad);

        // Recibimos la operación desde NivelesActivity (Suma, Resta, etc.)
        if (getIntent().hasExtra("TIPO_OPERACION")) {
            tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");
        }

        Button btnFacil = findViewById(R.id.btnFacil);
        Button btnMedio = findViewById(R.id.btnMedio);
        Button btnDificil = findViewById(R.id.btnDificil);

        // Nivel 1
        btnFacil.setOnClickListener(v -> iniciarJuego(1));

        // Nivel 2
        btnMedio.setOnClickListener(v -> iniciarJuego(2));

        // Nivel 3
        btnDificil.setOnClickListener(v -> iniciarJuego(3));
    }

    // Método para pasar la info al GameActivity
    private void iniciarJuego(int nivelDificultad) {
        Intent intent = new Intent(DificultadActivity.this, GameActivity.class);
        intent.putExtra("TIPO_OPERACION", tipoOperacion);
        intent.putExtra("NIVEL_DIFICULTAD", nivelDificultad);
        startActivity(intent);
    }
}