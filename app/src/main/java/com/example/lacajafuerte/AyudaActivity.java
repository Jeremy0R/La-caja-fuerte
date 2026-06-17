package com.example.lacajafuerte;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CLASE: AyudaActivity
 * PROPOSITO: Mostrar las instrucciones de juego y las reglas de puntuación al usuario.
 */
public class AyudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        Button btnEntendido = findViewById(R.id.btnEntendido);

        // Al presionar el botón, se cierra esta Activity y vuelve a la anterior
        btnEntendido.setOnClickListener(v -> finish());
    }
}