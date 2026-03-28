package com.example.dreammind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHabits = findViewById(R.id.buttonHabits);

        btnHabits.setOnClickListener(v -> {
            Intent intent = new Intent(this, HabitsListActivity.class);
            startActivity(intent);

        });
    }
}
