package com.example.lacajafuerte;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AjustesActivity extends AppCompatActivity {

    private GestorDatos gestorDatos;
    private boolean sonidoActivado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        gestorDatos = new GestorDatos(this);
        sonidoActivado = gestorDatos.isSonidoActivado();

        // NOTA: Ahora son ImageButton
        ImageButton btnToggleSonido = findViewById(R.id.btnToggleSonido);
        ImageButton btnReiniciar = findViewById(R.id.btnReiniciar);
        Button btnVolverAjustes = findViewById(R.id.btnVolverAjustes);

        // Configuramos el icono inicial
        actualizarIconoSonido(btnToggleSonido);

        btnToggleSonido.setOnClickListener(v -> {
            sonidoActivado = !sonidoActivado;
            gestorDatos.setSonidoActivado(sonidoActivado);
            actualizarIconoSonido(btnToggleSonido);
        });

        btnReiniciar.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("¿Estás seguro?")
                    .setMessage("Se borrarán todas tus coronas y niveles desbloqueados. Esto no se puede deshacer.")
                    .setPositiveButton("Sí, borrar", (dialog, which) -> {
                        gestorDatos.reiniciarProgreso();
                        Toast.makeText(AjustesActivity.this, "Progreso borrado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        btnVolverAjustes.setOnClickListener(v -> finish());
    }

    // Método actualizado para cambiar la imagen en lugar del texto
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