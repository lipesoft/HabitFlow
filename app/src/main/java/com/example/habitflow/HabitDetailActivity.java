package com.example.habitflow;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HabitDetailActivity extends AppCompatActivity {

    TextView title, description, status;
    Button btnComplete, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);

        title       = findViewById(R.id.textTitleDetail);
        description = findViewById(R.id.textDescriptionDetail);
        status      = findViewById(R.id.textStatusDetail);
        btnComplete = findViewById(R.id.buttonComplete);
        btnDelete   = findViewById(R.id.buttonDelete);

        String habitId          = getIntent().getStringExtra("id");
        String habitTitle       = getIntent().getStringExtra("title");
        String habitDescription = getIntent().getStringExtra("description");
        boolean completed       = getIntent().getBooleanExtra("status", false);
        int position            = getIntent().getIntExtra("position", -1);

        title.setText(habitTitle);
        description.setText(habitDescription);
        status.setText(completed ? "Concluído" : "Pendente");

        // Se já está concluído, desabilita o botão
        if (completed) btnComplete.setEnabled(false);

        btnComplete.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("completed", true);
            resultIntent.putExtra("id",        habitId);
            resultIntent.putExtra("position",  position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Excluir hábito")
                    .setMessage("Tem certeza que deseja excluir?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("delete",   true);
                        resultIntent.putExtra("id",       habitId);
                        resultIntent.putExtra("position", position);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }
}
