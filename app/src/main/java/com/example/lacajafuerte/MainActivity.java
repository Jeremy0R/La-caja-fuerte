package com.example.lacajafuerte;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Enlazamos el ImageView del diseño
        ImageView ivSplashGif = findViewById(R.id.ivSplashGif);

        // 2. Cargamos el GIF usando Glide
        Glide.with(this)
                .load(R.drawable.cfsplash)
                .into(ivSplashGif);

        // 3. Temporizador de 3 segundos para pasar al Menú
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Saltamos a MenuActivity (la crearemos a continuación)
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}