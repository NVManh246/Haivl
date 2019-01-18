package com.nvmanh.haivl.data.source.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.nvmanh.haivl.data.api.ApiFactory;
import com.nvmanh.haivl.data.api.HaivlApi;
import com.nvmanh.haivl.data.model.User;
import com.nvmanh.haivl.data.source.OnCompleteListener;
import com.nvmanh.haivl.data.source.UsersDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRemoteDataSource implements UsersDataSource.UsersRemoteDataSource {

    private static UsersRemoteDataSource sIntance;
    private HaivlApi mHaivlApi;

    private UsersRemoteDataSource() {
        mHaivlApi = ApiFactory.getApi();
    }

    public static UsersRemoteDataSource getIntance() {
        if(sIntance == null) {
            sIntance = new UsersRemoteDataSource();
        }
        return sIntance;
    }

    @Override
    public void login(String username, String password, final OnCompleteListener callback) {
        Call<User> call = mHaivlApi.login(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFail(t);
            }
        });
    }
}
