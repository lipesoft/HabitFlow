package com.example.dreammind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity{

    EditText editTitle, editDescription;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(v -> {

            String title = editTitle.getText().toString();
            String description = editDescription.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("description", description);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
