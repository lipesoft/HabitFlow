package com.example.habitflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(v -> {

            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs =
                    getSharedPreferences("habitflow_prefs", MODE_PRIVATE);

            String savedEmail =
                    prefs.getString("email", "");

            String savedPassword =
                    prefs.getString("senha", "");

            if (email.equals(savedEmail)
                    && password.equals(savedPassword)) {

                prefs.edit()
                        .putString("token", "TOKEN_LOCAL")
                        .apply();

                startActivity(
                        new Intent(LoginActivity.this,
                                HomeActivity.class));

                finish();

            } else {

                Toast.makeText(this,
                        "Email ou senha inválidos",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(
                    new Intent(LoginActivity.this,
                            RegisterActivity.class));
        });
    }
}