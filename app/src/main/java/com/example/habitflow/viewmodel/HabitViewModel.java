package com.example.habitflow.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.habitflow.data.model.Habit;
import com.example.habitflow.data.repository.HabitRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabitViewModel extends ViewModel {

    private final HabitRepository repository = new HabitRepository();
    public MutableLiveData<List<Habit>> habitsLiveData = new MutableLiveData<>();
    public MutableLiveData<Habit> habitLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public void getHabits(String token) {
        repository.getHabits(token).enqueue(new Callback<List<Habit>>() {
            @Override
            public void onResponse(Call<List<Habit>> call, Response<List<Habit>> response) {
                if (response.isSuccessful()) {
                    habitsLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Erro ao buscar hábitos.");
                }
            }

            @Override
            public void onFailure(Call<List<Habit>> call, Throwable t) {
                errorLiveData.setValue("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void createHabit(String token, String name, String description) {
        repository.createHabit(token, name, description).enqueue(new Callback<Habit>() {
            @Override
            public void onResponse(Call<Habit> call, Response<Habit> response) {
                if (response.isSuccessful()) {
                    habitLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Erro ao criar hábito.");
                }
            }

            @Override
            public void onFailure(Call<Habit> call, Throwable t) {
                errorLiveData.setValue("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void deleteHabit(String token, String id) {
        repository.deleteHabit(token, id).enqueue(new Callback<Habit>() {
            @Override
            public void onResponse(Call<Habit> call, Response<Habit> response) {
                if (!response.isSuccessful()) {
                    errorLiveData.setValue("Erro ao excluir hábito.");
                }
            }

            @Override
            public void onFailure(Call<Habit> call, Throwable t) {
                errorLiveData.setValue("Erro de conexão: " + t.getMessage());
            }
        });
    }
}