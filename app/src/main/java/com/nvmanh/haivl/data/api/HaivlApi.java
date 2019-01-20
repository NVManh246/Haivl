package com.nvmanh.haivl.data.api;

import com.nvmanh.haivl.data.model.Post;
import com.nvmanh.haivl.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HaivlApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("username") String username, @Field("password") String password);

    @GET("get_posts.php")
    Call<List<Post>> getPosts(@Query("username") String myUsername);
}
