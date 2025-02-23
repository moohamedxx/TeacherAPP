package com.example.teacherapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button btnCat = findViewById(R.id.btnCat);
        Button btnDog = findViewById(R.id.btnDog);
        Button btnLion = findViewById(R.id.btnLion);
        Button btnElephant = findViewById(R.id.btnElephant);
        Button btnTiger = findViewById(R.id.btnTiger);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle language not supported
                }
            } else {
                // Handle TextToSpeech initialization failure
            }
        });

        // Set click listeners for buttons
        btnCat.setOnClickListener(v -> speakText(btnCat.getText().toString()));
        btnDog.setOnClickListener(v -> speakText(btnDog.getText().toString()));
        btnLion.setOnClickListener(v -> speakText(btnLion.getText().toString()));
        btnElephant.setOnClickListener(v -> speakText(btnElephant.getText().toString()));
        btnTiger.setOnClickListener(v -> speakText(btnTiger.getText().toString()));
    }

    // Method to speak the text
    private void speakText(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        // Shutdown TextToSpeech when the activity is destroyed
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}