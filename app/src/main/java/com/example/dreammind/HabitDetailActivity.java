package com.example.dreammind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HabitDetailActivity extends AppCompatActivity {

    TextView title, description, status;
    Button btnComplete;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_habit_detail);

        title = findViewById(R.id.textTitleDetail);
        description = findViewById(R.id.textDescriptionDetail);
        status = findViewById(R.id.textStatusDetail);
        btnComplete = findViewById(R.id.buttonComplete);

        String habitTitle = getIntent().getStringExtra("title");
        String habitDescription = getIntent().getStringExtra("description");
        boolean completed = getIntent().getBooleanExtra("status", false);

        title.setText(habitTitle);
        description.setText(habitDescription);
        status.setText(completed ? "Concluído" : "Pendente");

        btnComplete.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("completed", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
