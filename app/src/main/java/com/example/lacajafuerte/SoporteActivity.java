package com.example.lacajafuerte;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton; // Importación necesaria para el botón
import androidx.appcompat.app.AppCompatActivity;

public class SoporteActivity extends AppCompatActivity {

    private final String NUMERO_SOPORTE = "9711393532";
    private MediaPlayer mediaPlayer; // Variable para la música

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        // ==========================================
        // INICIAR LA MÚSICA DE FONDO
        // ==========================================
        mediaPlayer = MediaPlayer.create(this, R.raw.dog);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true); // Para que se repita infinitamente
            mediaPlayer.start();
        }

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnLlamar = findViewById(R.id.btnLlamar);
        Button btnSms = findViewById(R.id.btnSms);

        // Lógica para cerrar la pantalla de créditos
        btnBack.setOnClickListener(v -> finish());

        btnLlamar.setOnClickListener(v -> {
            Intent intentLlamada = new Intent(Intent.ACTION_DIAL);
            intentLlamada.setData(Uri.parse("tel:" + NUMERO_SOPORTE));
            startActivity(intentLlamada);
        });

        btnSms.setOnClickListener(v -> {
            Intent intentSms = new Intent(Intent.ACTION_VIEW);
            intentSms.setData(Uri.parse("sms:" + NUMERO_SOPORTE));
            intentSms.putExtra("sms_body", "Hola, necesito ayuda con el juego de La Caja Fuerte.");
            startActivity(intentSms);
        });
    }

    // ==========================================
    // CONTROLADORES DEL CICLO DE VIDA DE LA APP
    // ==========================================

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}