package com.example.dreammind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;

public class HabitListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HabitAdapter adapter;
    List<Habit> habitList;
    TextView textEmpty;

    private ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK) {

                            Intent data = result.getData();

                            int position = data.getIntExtra("position", -1);

                            if (position != -1) {

                                if (data.getBooleanExtra("delete", false)) {
                                    habitList.remove(position);
                                }

                                else if (data.getBooleanExtra("completed", false)) {
                                    habitList.get(position).setCompleted(true);
                                }

                                adapter.notifyDataSetChanged();
                                updateEmptyState();
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);

        recyclerView = findViewById(R.id.recyclerHabits);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textEmpty = findViewById(R.id.textEmpty);

        habitList = new ArrayList<>();

        //Dados de teste
        habitList.add(new Habit("Beber água", "2 litros por dia", false));
        habitList.add(new Habit("Academia", "Treinar 3x por semana", true));
        habitList.add(new Habit("Estudar", "1 hora por dia", false));

        adapter = new HabitAdapter(habitList, position -> {
            Habit habit = habitList.get(position);

            Intent intent = new Intent(this, HabitDetailActivity.class);

            intent.putExtra("title", habit.getTitle());
            intent.putExtra("description", habit.getDescription());
            intent.putExtra("status", habit.isCompleted());
            intent.putExtra("position", position);

           detailLauncher.launch(intent);
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddHabitActivity.class);
            addLauncher.launch(intent);
        });

        updateEmptyState();

    }

    private ActivityResultLauncher<Intent> addLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            String title = result.getData().getStringExtra("title");
                            String description = result.getData().getStringExtra("description");

                            habitList.add(new Habit(title, description, false));

                            adapter.notifyDataSetChanged();
                            updateEmptyState();
                        }
                    }
            );

    private void updateEmptyState() {
        if (habitList.isEmpty()) {
            textEmpty.setVisibility(View.VISIBLE);
        } else {
            textEmpty.setVisibility(View.GONE);
        }
    }
}



