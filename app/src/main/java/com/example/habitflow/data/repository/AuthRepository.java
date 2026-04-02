package com.example.habitflow.data.repository;

import com.example.habitflow.data.model.User;
import com.example.habitflow.data.network.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class AuthRepository {

    public Call<User> login(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        return RetrofitClient.getApiService().login(body);
    }

    public Call<User> register(String name, String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        body.put("email", email);
        body.put("password", password);
        return RetrofitClient.getApiService().register(body);
    }
}