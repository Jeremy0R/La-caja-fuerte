package com.example.lacajafuerte;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class VictoriaActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayerVictoria;
    private GestorDatos gestorDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victoria);

        gestorDatos = new GestorDatos(this);

        if (gestorDatos.isSonidoActivado()) {
            mediaPlayerVictoria = MediaPlayer.create(this, R.raw.happy);
            if (mediaPlayerVictoria != null) {
                mediaPlayerVictoria.start();
            }
        }

        ImageView ivCajaAbierta = findViewById(R.id.ivCajaAbierta);

        Glide.with(this)
                .asGif()
                .load(R.drawable.victorygif)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (resource != null) {
                            resource.setLoopCount(1);
                        }
                        return false;
                    }
                })
                .into(ivCajaAbierta);


        TextView tvPuntajeFinal = findViewById(R.id.tvPuntajeFinal);
        TextView tvRecompensa = findViewById(R.id.tvRecompensa);
        Button btnVolverMenu = findViewById(R.id.btnVolverMenu);

        // Cambia el texto del botón dinámicamente para que tenga sentido (Opcional)
        btnVolverMenu.setText("SIGUIENTE NIVEL");

        int puntaje = getIntent().getIntExtra("PUNTAJE_FINAL", 0);
        // Recibimos la operación que venía del juego
        String tipoOperacion = getIntent().getStringExtra("TIPO_OPERACION");
        if (tipoOperacion == null) tipoOperacion = "SUMA"; // Por seguridad

        tvPuntajeFinal.setText("Desempeño: " + puntaje);

        if (puntaje == 100) {
            tvRecompensa.setText("Perfección: +100\nTotal: +200 👑");
        } else {
            tvRecompensa.setText("Total: +" + puntaje + " 👑");
        }

        // Para usar la variable dentro del lambda, necesitamos hacerla "final" o usar una copia final
        final String operacionFinal = tipoOperacion;

        btnVolverMenu.setOnClickListener(v -> {
            if (mediaPlayerVictoria != null) {
                mediaPlayerVictoria.release();
                mediaPlayerVictoria = null;
            }

            // Cambiamos el destino a NivelesActivity y le mandamos la operación
            Intent intent = new Intent(VictoriaActivity.this, NivelesActivity.class);
            intent.putExtra("TIPO_OPERACION", operacionFinal);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayerVictoria != null && mediaPlayerVictoria.isPlaying()) {
            mediaPlayerVictoria.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerVictoria != null) {
            mediaPlayerVictoria.release();
            mediaPlayerVictoria = null;
        }
    }
}