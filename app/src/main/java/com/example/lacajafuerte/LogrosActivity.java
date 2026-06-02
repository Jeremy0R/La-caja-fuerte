package com.example.lacajafuerte;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LogrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logros);

        ImageButton btnBack = findViewById(R.id.btnBackLogros);
        TextView tvTotalCoronas = findViewById(R.id.tvTotalCoronas);

        // Volver al menú
        btnBack.setOnClickListener(v -> finish());

        // Leemos las coronas guardadas y las mostramos
        GestorDatos gestor = new GestorDatos(this);
        int coronas = gestor.obtenerCoronas();

        tvTotalCoronas.setText(coronas + " CORONAS");
    }
}