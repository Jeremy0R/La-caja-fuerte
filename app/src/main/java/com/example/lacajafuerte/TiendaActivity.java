package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton; // ¡Importante agregar esto!
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TiendaActivity extends AppCompatActivity {

    private GestorDatos gestorDatos;
    private TextView tvMisCoronas;
    private Button btnSumas, btnRestas, btnMult, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        gestorDatos = new GestorDatos(this);
        tvMisCoronas = findViewById(R.id.tvMisCoronas);

        btnSumas = findViewById(R.id.btnComprarSumas);
        btnRestas = findViewById(R.id.btnComprarRestas);
        btnMult = findViewById(R.id.btnComprarMult);
        btnDiv = findViewById(R.id.btnComprarDiv);

        // Enlazamos el nuevo botón circular
        ImageButton btnBack = findViewById(R.id.btnBack);

        actualizarUI();

        btnSumas.setOnClickListener(v -> irAMapaNiveles("SUMA"));

        // Ajustamos los parámetros de costo de la función (100, 300, 500)
        btnRestas.setOnClickListener(v -> manejarClickOperacion("RESTA", 100, btnRestas));
        btnMult.setOnClickListener(v -> manejarClickOperacion("MULT", 300, btnMult));
        btnDiv.setOnClickListener(v -> manejarClickOperacion("DIV", 500, btnDiv));

        btnBack.setOnClickListener(v -> finish());
    }

    private void manejarClickOperacion(String operacion, int costo, Button boton) {
        if (gestorDatos.isOperacionDesbloqueada(operacion)) {
            irAMapaNiveles(operacion);
        } else {
            if (gestorDatos.gastarCoronas(costo)) {
                gestorDatos.desbloquearOperacion(operacion);
                Toast.makeText(this, "¡Desbloqueado exitosamente!", Toast.LENGTH_SHORT).show();
                actualizarUI();
            } else {
                Toast.makeText(this, "No tienes suficientes coronas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void irAMapaNiveles(String tipoOperacion) {
        Intent intent = new Intent(TiendaActivity.this, NivelesActivity.class); // <-- Asegúrate de que diga NivelesActivity
        intent.putExtra("TIPO_OPERACION", tipoOperacion);
        startActivity(intent);
    }

    private void actualizarUI() {
        tvMisCoronas.setText("Mis Coronas: " + gestorDatos.obtenerCoronas());
        btnSumas.setText("JUGAR SUMAS");

        if (gestorDatos.isOperacionDesbloqueada("RESTA")) {
            btnRestas.setText("JUGAR RESTAS");
        } else {
            btnRestas.setText("Desbloquear Restas\n(100 Coronas)");
        }

        if (gestorDatos.isOperacionDesbloqueada("MULT")) {
            btnMult.setText("JUGAR MULTIPLICACIONES");
        } else {
            btnMult.setText("Desbloquear Multiplicación\n(300 Coronas)");
        }

        if (gestorDatos.isOperacionDesbloqueada("DIV")) {
            btnDiv.setText("JUGAR DIVISIONES");
        } else {
            btnDiv.setText("Desbloquear División\n(500 Coronas)");
        }
    }
}