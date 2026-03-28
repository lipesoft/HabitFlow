package com.example.dreammind;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);

        recyclerView = findViewById(R.id.recyclerHabits);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        habitList = new ArrayList<>();

        //Dados de teste
        habitList.add(new Habit("Beber água", "2 litros por dia", false));
        habitList.add(new Habit("Academia", "Treinar 3x por semana", true));
        habitList.add(new Habit("Estudar", "1 hora por dia", false));

        adapter = new HabitAdapter(habitList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddHabitActivity.class);
            startActivity(intent);
        });
    }
}
