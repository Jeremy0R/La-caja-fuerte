package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnNiveles = findViewById(R.id.btnNiveles);
        ImageButton btnCorona = findViewById(R.id.btnCorona);

        // Al hacer clic en PLAY, vamos a la pantalla de Juego
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            startActivity(intent);
        });

        btnCorona.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, LogrosActivity.class);
            startActivity(intent);
        });

        // Al hacer clic en NIVELES, vamos a la pantalla de Niveles
        btnNiveles.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, NivelesActivity.class);
            startActivity(intent);
        });
    }
}