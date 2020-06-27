package com.example.cachewithroom.apiClass;

import com.example.cachewithroom.pojoClass.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users")
    Call<List<User>> getUser();
}
