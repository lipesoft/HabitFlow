package com.example.habitflow.data.network;

import com.example.habitflow.data.model.Habit;
import com.example.habitflow.data.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    // Auth
    @POST("auth/register")
    Call<User> register(@Body Map<String, String> body);
    @POST("auth/login")
    Call<User> login(@Body Map<String, String> body);
    // Habits
    @GET("habits")
    Call<List<Habit>> getHabits(@Header("Authorization") String token);
    @POST("habits")
    Call<Habit> createHabit(@Header("Authorization") String token, @Body Map<String, String> body);
    @PUT("habits/{id}")
    Call<Habit> updateHabit(@Header("Authorization") String token, @Path("id") String id, @Body Map<String, String> body);
    @DELETE("habits/{id}")
    Call<Habit> deleteHabit(@Header("Authorization") String token, @Path("id") String id);
}