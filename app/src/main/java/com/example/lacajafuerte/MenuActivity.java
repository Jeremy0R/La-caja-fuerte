package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnNiveles = findViewById(R.id.btnNiveles);
        ImageButton btnAjustes = findViewById(R.id.btnAjustes);
        ImageButton btnCorona = findViewById(R.id.btnCorona);
        ImageButton btnEstrella = findViewById(R.id.btnEstrella);
        ImageButton btnCreditos = findViewById(R.id.btnCreditos);

        // 1. PLAY abre la selección de operaciones (NivelesActivity)
        btnPlay.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, NivelesActivity.class)));

        // 2. El segundo botón (que ahora visualmente será "LA BÓVEDA") abre la tienda
        btnNiveles.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, TiendaActivity.class)));

        btnCorona.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, LogrosActivity.class)));

        btnAjustes.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, AjustesActivity.class)));

        btnEstrella.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, AyudaActivity.class))); // Aquí va el Cómo Jugar

        btnCreditos.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, SoporteActivity.class))); // Aquí va el SMS/Llamada
    }
}