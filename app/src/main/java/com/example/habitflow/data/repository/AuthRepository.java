package com.example.habitflow.data.repository;

import com.example.habitflow.data.model.User;

import retrofit2.Call;
import retrofit2.mock.Calls;

public class AuthRepository {

    public Call<User> login(String email, String password) {

        User user = new User();

        user.setEmail(email);
        user.setToken("TOKEN_LOCAL");

        return Calls.response(user);
    }

    public Call<User> register(String nome,
                               String email,
                               String password) {

        User user = new User();

        user.setNome(nome);
        user.setEmail(email);
        user.setToken("TOKEN_LOCAL");

        return Calls.response(user);
    }
}