package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton; // ¡Importante!
import androidx.appcompat.app.AppCompatActivity;

/**
 * CLASE: PausaActivity
 * PROPOSITO: Interrumpir la partida actual ofreciendo opciones para continuar o abandonar.
 */
public class PausaActivity extends AppCompatActivity {

    private GestorDatos gestorDatos;
    private boolean sonidoActivado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa);

        gestorDatos = new GestorDatos(this);
        sonidoActivado = gestorDatos.isSonidoActivado();

        Button btnReanudar = findViewById(R.id.btnReanudar);
        Button btnSalirMenu = findViewById(R.id.btnSalirMenu);
        ImageButton btnToggleSonidoPausa = findViewById(R.id.btnToggleSonidoPausa);

        // Configuramos el icono de la bocina apenas se abre la pausa
        actualizarIconoSonido(btnToggleSonidoPausa);

        // LOGICA REANUDAR
        btnReanudar.setOnClickListener(v -> finish());

        // LOGICA SALIR
        btnSalirMenu.setOnClickListener(v -> {
            Intent intent = new Intent(PausaActivity.this, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // LOGICA TOGGLE SONIDO
        btnToggleSonidoPausa.setOnClickListener(v -> {
            sonidoActivado = !sonidoActivado;
            gestorDatos.setSonidoActivado(sonidoActivado); // Se guarda en memoria al instante
            actualizarIconoSonido(btnToggleSonidoPausa);
        });
    }

    // Método para cambiar la imagen de la bocina dinámicamente
    private void actualizarIconoSonido(ImageButton boton) {
        if (sonidoActivado) {
            // Icono de bocina encendida
            boton.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
        } else {
            // Icono de bocina apagada (modo silencio)
            boton.setImageResource(android.R.drawable.ic_lock_silent_mode);
        }
    }
}