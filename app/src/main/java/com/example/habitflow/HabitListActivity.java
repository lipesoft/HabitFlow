package com.example.habitflow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitflow.data.model.Habit;
import com.example.habitflow.viewmodel.HabitViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HabitListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HabitAdapter adapter;
    List<Habit> habitList = new ArrayList<>();
    TextView textEmpty;
    HabitViewModel habitViewModel;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);

        // Recupera o token salvo
        SharedPreferences prefs = getSharedPreferences("habitflow_prefs", MODE_PRIVATE);
        token = prefs.getString("token", "");

        recyclerView = findViewById(R.id.recyclerHabits);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textEmpty = findViewById(R.id.textEmpty);

        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);

        adapter = new HabitAdapter(habitList, position -> {
            Habit habit = habitList.get(position);
            Intent intent = new Intent(this, HabitDetailActivity.class);
            intent.putExtra("id",          habit.getId());
            intent.putExtra("title",       habit.getName());
            intent.putExtra("description", habit.getDescription());
            intent.putExtra("status",      habit.isCompleted());
            intent.putExtra("position",    position);
            detailLauncher.launch(intent);
        });
        recyclerView.setAdapter(adapter);

        // Observa a lista de hábitos
        habitViewModel.habitsLiveData.observe(this, habits -> {
            habitList.clear();
            if (habits != null) habitList.addAll(habits);
            adapter.notifyDataSetChanged();
            updateEmptyState();
        });

        // Observa criação de novo hábito → recarrega lista
        habitViewModel.habitLiveData.observe(this, habit -> {
            if (habit != null) {
                habitViewModel.getHabits(token);
            }
        });

        habitViewModel.errorLiveData.observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        // Carrega hábitos da API
        habitViewModel.getHabits(token);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> addLauncher.launch(
                new Intent(this, AddHabitActivity.class)
        ));
    }

    // Resultado do HabitDetailActivity (delete / complete)
    private final ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data     = result.getData();
                            String habitId  = data.getStringExtra("id");
                            int position    = data.getIntExtra("position", -1);

                            if (data.getBooleanExtra("delete", false) && habitId != null) {
                                habitViewModel.deleteHabit(token, habitId);
                                if (position != -1) habitList.remove(position);
                                adapter.notifyDataSetChanged();
                                updateEmptyState();

                            } else if (data.getBooleanExtra("completed", false) && habitId != null) {
                                habitViewModel.markCompleted(token, habitId);
                                if (position != -1) habitList.get(position).setCompleted(true);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
            );

    // Resultado do AddHabitActivity
    private final ActivityResultLauncher<Intent> addLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            String name        = result.getData().getStringExtra("title");
                            String description = result.getData().getStringExtra("description");
                            habitViewModel.createHabit(token, name, description);
                        }
                    }
            );

    private void updateEmptyState() {
        textEmpty.setVisibility(habitList.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
