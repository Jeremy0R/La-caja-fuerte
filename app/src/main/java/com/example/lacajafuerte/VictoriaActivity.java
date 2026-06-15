package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VictoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victoria);

        TextView tvPuntajeFinal = findViewById(R.id.tvPuntajeFinal);
        Button btnVolverMenu = findViewById(R.id.btnVolverMenu);

        // Recibimos el puntaje que mandó GameActivity
        int puntaje = getIntent().getIntExtra("PUNTAJE_FINAL", 0);

        // Lo mostramos en pantalla
        tvPuntajeFinal.setText("Puntaje: " + puntaje);

        // Botón para regresar al menú principal
        btnVolverMenu.setOnClickListener(v -> {
            Intent intent = new Intent(VictoriaActivity.this, MenuActivity.class);
            // Limpiamos el historial de pantallas para que no se apilen
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}