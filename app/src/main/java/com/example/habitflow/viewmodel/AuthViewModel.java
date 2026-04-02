package com.example.habitflow.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.habitflow.data.model.User;
import com.example.habitflow.data.repository.AuthRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private final AuthRepository repository = new AuthRepository();
    public MutableLiveData<User> userLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public void login(String email, String password) {
        repository.login(email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Email ou senha inválidos.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                errorLiveData.setValue("Erro de conexão: " + t.getMessage());
            }
        });
    }

    public void register(String name, String email, String password) {
        repository.register(name, email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                } else {
                    errorLiveData.setValue("Erro ao cadastrar.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                errorLiveData.setValue("Erro de conexão: " + t.getMessage());
            }
        });
    }
}