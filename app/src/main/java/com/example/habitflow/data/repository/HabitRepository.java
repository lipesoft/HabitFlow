package com.example.habitflow.data.repository;

import com.example.habitflow.data.model.Habit;
import com.example.habitflow.data.network.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class HabitRepository {

    public Call<List<Habit>> getHabits(String token) {
        return RetrofitClient.getApiService().getHabits("Bearer " + token);
    }

    public Call<Habit> createHabit(String token, String name, String description) {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("description", description);
        return RetrofitClient.getApiService().createHabit("Bearer " + token, body);
    }

    public Call<Habit> updateHabit(String token, String id, String name, String description) {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("description", description);
        return RetrofitClient.getApiService().updateHabit("Bearer " + token, id, body);
    }

    public Call<Habit> deleteHabit(String token, String id) {
        return RetrofitClient.getApiService().deleteHabit("Bearer " + token, id);
    }
}