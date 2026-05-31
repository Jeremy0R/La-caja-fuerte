package com.example.lacajafuerte;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnNiveles = findViewById(R.id.btnNiveles);

        btnPlay.setOnClickListener(v -> {
            // Aquí irá el Intent hacia GameActivity o LevelSelectionActivity
            Toast.makeText(this, "¡Iniciando Juego!", Toast.LENGTH_SHORT).show();
        });

        btnNiveles.setOnClickListener(v -> {
            // Aquí irá el Intent hacia LevelSelectionActivity
            Toast.makeText(this, "Abriendo Niveles...", Toast.LENGTH_SHORT).show();
        });
    }
}