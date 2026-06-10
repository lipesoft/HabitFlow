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

public class RegisterActivity extends AppCompatActivity {

    EditText editName, editEmail, editPassword;
    Button btnRegister;
    AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName     = findViewById(R.id.editName);
        editEmail    = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnRegister  = findViewById(R.id.buttonRegister);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.userLiveData.observe(this, user -> {
            if (user != null) {
                Toast.makeText(this, "Conta criada! Faça login.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        });

        authViewModel.errorLiveData.observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {

            String name = editName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs =
                    getSharedPreferences("habitflow_prefs", MODE_PRIVATE);

            prefs.edit()
                    .putString("nome", name)
                    .putString("email", email)
                    .putString("senha", password)
                    .apply();

            Toast.makeText(this,
                    "Conta criada com sucesso!",
                    Toast.LENGTH_SHORT).show();

            startActivity(
                    new Intent(this, LoginActivity.class));

            finish();
        });
    }
}
