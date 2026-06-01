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

        // El botón de atrás simplemente "cierra" esta pantalla y vuelve al menú
        btnBack.setOnClickListener(v -> finish());

        // El botón de Sumas abre el juego y le avisa que debe generar sumas
        btnSumas.setOnClickListener(v -> {
            Intent intent = new Intent(NivelesActivity.this, GameActivity.class);
            intent.putExtra("TIPO_OPERACION", "SUMA");
            startActivity(intent);
        });
    }
}