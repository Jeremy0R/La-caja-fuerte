package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class NivelesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnSumas = findViewById(R.id.btnSumas);
        Button btnRestas = findViewById(R.id.btnRestas);
        Button btnMult = findViewById(R.id.btnMult);
        Button btnDiv = findViewById(R.id.btnDiv);

        // El botón de atrás simplemente "cierra" esta pantalla y vuelve al menú
        btnBack.setOnClickListener(v -> finish());

        // Configuración de los botones para cada operación
        btnSumas.setOnClickListener(v -> iniciarJuego("SUMA"));
        btnRestas.setOnClickListener(v -> iniciarJuego("RESTA"));
        btnMult.setOnClickListener(v -> iniciarJuego("MULT"));
        btnDiv.setOnClickListener(v -> iniciarJuego("DIV"));
    }

    private void iniciarJuego(String tipo) {
        Intent intent = new Intent(NivelesActivity.this, GameActivity.class);
        intent.putExtra("TIPO_OPERACION", tipo);
        startActivity(intent);
    }
}