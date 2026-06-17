package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CLASE: PausaActivity
 * PROPOSITO: Interrumpir la partida actual ofreciendo opciones para continuar o abandonar.
 */
public class PausaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa);

        Button btnReanudar = findViewById(R.id.btnReanudar);
        Button btnSalirMenu = findViewById(R.id.btnSalirMenu);

        // LOGICA REANUDAR: Solo cerramos esta pantalla emergente
        btnReanudar.setOnClickListener(v -> finish());

        // LOGICA SALIR: Regresamos al menú y limpiamos el historial
        btnSalirMenu.setOnClickListener(v -> {
            Intent intent = new Intent(PausaActivity.this, MenuActivity.class);
            // Esto evita que si le das "Atrás" en el menú, te regrese al juego pausado
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}