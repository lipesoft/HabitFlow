package com.example.habitflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnHabits, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHabits = findViewById(R.id.buttonHabits);
        btnLogout = findViewById(R.id.buttonLogout);

        btnHabits.setOnClickListener(v -> startActivity(new Intent(this, HabitListActivity.class)));

        btnLogout.setOnClickListener(v -> {
            // Limpa o token salvo
            SharedPreferences prefs = getSharedPreferences("habitflow_prefs", MODE_PRIVATE);
            prefs.edit().remove("token").apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
