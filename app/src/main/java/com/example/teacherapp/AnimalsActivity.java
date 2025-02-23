package com.example.teacherapp;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class AnimalsActivity extends AppCompatActivity {
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        setupButton(R.id.btnCat, "Cat");
        setupButton(R.id.btnDog, "Dog");
        setupButton(R.id.btnLion, "Lion");
        setupButton(R.id.btnElephant, "Elephant");
        setupButton(R.id.btnTiger, "Tiger");
    }

    private void setupButton(int id, String text) {
        Button button = findViewById(id);
        if (button != null) {
            button.setOnClickListener(v -> tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null));
        } else {
            // Log error if the button is not found
            Log.e("AnimalsActivity", "Button with ID " + id + " not found.");
        }
    }


    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}