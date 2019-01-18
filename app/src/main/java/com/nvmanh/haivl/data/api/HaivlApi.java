package com.nvmanh.haivl.data.api;

import com.nvmanh.haivl.data.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HaivlApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("username") String username, @Field("password") String password);
}
