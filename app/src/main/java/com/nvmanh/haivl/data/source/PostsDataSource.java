package com.nvmanh.haivl.data.source;

public interface PostsDataSource {
    interface PostsRemoteDataSource {
        void getPosts(String myUsername, OnCompleteListener callback);
    }
}
