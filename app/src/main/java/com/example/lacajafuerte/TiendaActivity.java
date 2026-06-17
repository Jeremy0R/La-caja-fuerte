package com.example.lacajafuerte;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * CLASE: TiendaActivity
 * PROPOSITO: Permitir al usuario canjear sus coronas acumuladas por nuevos niveles.
 */
public class TiendaActivity extends AppCompatActivity {

    private GestorDatos gestorDatos;
    private TextView tvMisCoronas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        // Instanciamos nuestro gestor de persistencia
        gestorDatos = new GestorDatos(this);

        tvMisCoronas = findViewById(R.id.tvMisCoronas);
        Button btnComprarRestas = findViewById(R.id.btnComprarRestas);
        Button btnComprarMult = findViewById(R.id.btnComprarMult);
        Button btnVolverTienda = findViewById(R.id.btnVolverTienda);

        // Mostramos las coronas actuales al abrir la pantalla
        actualizarTextoCoronas();

        // LOGICA DE COMPRA: Restas
        btnComprarRestas.setOnClickListener(v -> {
            if (gestorDatos.gastarCoronas(3)) {
                Toast.makeText(this, "¡Restas Desbloqueadas!", Toast.LENGTH_SHORT).show();
                actualizarTextoCoronas();
                // Aquí podrías usar SharedPreferences para guardar que ya se compró este nivel
                btnComprarRestas.setText("RESTAS (DESBLOQUEADO)");
                btnComprarRestas.setEnabled(false);
            } else {
                Toast.makeText(this, "No tienes suficientes coronas", Toast.LENGTH_SHORT).show();
            }
        });

        // LOGICA DE COMPRA: Multiplicaciones
        btnComprarMult.setOnClickListener(v -> {
            if (gestorDatos.gastarCoronas(5)) {
                Toast.makeText(this, "¡Multiplicaciones Desbloqueadas!", Toast.LENGTH_SHORT).show();
                actualizarTextoCoronas();
                btnComprarMult.setText("MULTIPLICACIÓN (DESBLOQUEADO)");
                btnComprarMult.setEnabled(false);
            } else {
                Toast.makeText(this, "¡Sigue jugando para ganar más coronas!", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para regresar
        btnVolverTienda.setOnClickListener(v -> finish());
    }

    /**
     * METODO: actualizarTextoCoronas
     * Consulta a SharedPreferences y actualiza el TextView en la interfaz.
     */
    private void actualizarTextoCoronas() {
        int coronas = gestorDatos.obtenerCoronas();
        tvMisCoronas.setText("Mis Coronas: " + coronas);
    }
}