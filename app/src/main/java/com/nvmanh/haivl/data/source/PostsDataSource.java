package com.nvmanh.haivl.data.source;

public interface PostsDataSource {
    interface PostsRemoteDataSource {
        void getPosts(String myUsername, int page, OnCompleteListener callback);
        void like(String myUsername, int postId, boolean isLike);
    }
}
