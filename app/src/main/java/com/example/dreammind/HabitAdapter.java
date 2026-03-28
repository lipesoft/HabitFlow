package com.example.dreammind;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.MyViewHolder> {

    private List<Habit> habitList;

    public HabitAdapter(List<Habit> habitList) {
        this.habitList = habitList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, status;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            status = itemView.findViewById(R.id.textStatus);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Habit habit = habitList.get(position);

        holder.title.setText(habit.getTitle());
        holder.description.setText(habit.getDescription());
        holder.status.setText(habit.isCompleted() ? "Concluído" : "Pendente");
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }
}