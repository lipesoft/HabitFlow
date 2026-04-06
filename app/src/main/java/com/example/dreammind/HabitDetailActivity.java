package com.example.dreammind;

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
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_habit_detail);

        title = findViewById(R.id.textTitleDetail);
        description = findViewById(R.id.textDescriptionDetail);
        status = findViewById(R.id.textStatusDetail);
        btnComplete = findViewById(R.id.buttonComplete);
        btnDelete = findViewById(R.id.buttonDelete);

        String habitTitle = getIntent().getStringExtra("title");
        String habitDescription = getIntent().getStringExtra("description");
        boolean completed = getIntent().getBooleanExtra("status", false);
        int position = getIntent().getIntExtra("position", -1);

        title.setText(habitTitle);
        description.setText(habitDescription);
        status.setText(completed ? "Concluído" : "Pendente");

        btnComplete.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("completed", true);
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();

            Toast.makeText(this,"Hábito concluído", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Excluir hábito")
                    .setMessage("Tem certeza que deseja excluir?")
                    .setPositiveButton("Sim", (dialog, whick) -> {

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("delete", true);
                        resultIntent.putExtra("position", position);

                        setResult(RESULT_OK, resultIntent);
                        finish();

                        Toast.makeText(this, "Hábito excluído!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }
}
