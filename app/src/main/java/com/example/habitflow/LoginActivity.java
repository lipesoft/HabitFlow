package com.example.habitflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.habitflow.viewmodel.AuthViewModel;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin, btnRegister;
    AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail    = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin     = findViewById(R.id.buttonLogin);
        btnRegister  = findViewById(R.id.buttonRegister);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        // Observa login com sucesso
        authViewModel.userLiveData.observe(this, user -> {
            if (user != null && user.getToken() != null) {
                // Salva o token no SharedPreferences
                SharedPreferences prefs = getSharedPreferences("habitflow_prefs", MODE_PRIVATE);
                prefs.edit().putString("token", user.getToken()).apply();

                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        });

        // Observa erros
        authViewModel.errorLiveData.observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(v -> {
            String email    = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            authViewModel.login(email, password);
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
