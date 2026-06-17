package com.example.lacajafuerte;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SoporteActivity extends AppCompatActivity {

    // Pon un número de prueba aquí
    private final String NUMERO_SOPORTE = "9711393532";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        Button btnLlamar = findViewById(R.id.btnLlamar);
        Button btnSms = findViewById(R.id.btnSms);

        // Lógica para hacer la llamada
        btnLlamar.setOnClickListener(v -> {
            Intent intentLlamada = new Intent(Intent.ACTION_DIAL);
            intentLlamada.setData(Uri.parse("tel:" + NUMERO_SOPORTE));
            startActivity(intentLlamada);
        });

        // Lógica para enviar el SMS
        btnSms.setOnClickListener(v -> {
            Intent intentSms = new Intent(Intent.ACTION_VIEW);
            intentSms.setData(Uri.parse("sms:" + NUMERO_SOPORTE));
            intentSms.putExtra("sms_body", "Hola, necesito ayuda con el juego de La Caja Fuerte.");
            startActivity(intentSms);
        });
    }
}