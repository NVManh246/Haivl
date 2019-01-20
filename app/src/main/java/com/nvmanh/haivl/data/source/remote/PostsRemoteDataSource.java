package com.nvmanh.haivl.data.source.remote;

import android.util.Log;

import com.nvmanh.haivl.data.api.ApiFactory;
import com.nvmanh.haivl.data.api.HaivlApi;
import com.nvmanh.haivl.data.model.Post;
import com.nvmanh.haivl.data.source.OnCompleteListener;
import com.nvmanh.haivl.data.source.PostsDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRemoteDataSource implements PostsDataSource.PostsRemoteDataSource {

    private static PostsRemoteDataSource sIntance;
    private HaivlApi mHaivlApi;

    private PostsRemoteDataSource(){
        mHaivlApi = ApiFactory.getApi();
    }

    public static PostsRemoteDataSource getIntance() {
        if(sIntance == null) {
            sIntance = new PostsRemoteDataSource();
        }
        return sIntance;
    }

    @Override
    public void getPosts(String myUsername, final OnCompleteListener callback) {
        Call<List<Post>> call = mHaivlApi.getPosts(myUsername);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onFail(t);
            }
        });
    }
}
