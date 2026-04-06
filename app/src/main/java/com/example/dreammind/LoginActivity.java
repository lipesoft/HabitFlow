package com.example.dreammind;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });
    }
}