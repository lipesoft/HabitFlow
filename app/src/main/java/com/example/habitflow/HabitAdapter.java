package com.example.habitflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitflow.data.model.Habit;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.MyViewHolder> {

    private final List<Habit> habitList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public HabitAdapter(List<Habit> habitList, OnItemClickListener listener) {
        this.habitList = habitList;
        this.listener  = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, status;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            status      = itemView.findViewById(R.id.textStatus);
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

        holder.title.setText(habit.getName());
        holder.description.setText(habit.getDescription());

        if(habit.isCompleted()){
            holder.status.setText("🟢 Concluído");
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.success));
        }else{
            holder.status.setText("🟡 Em andamento");
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.secondary));
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }
}
