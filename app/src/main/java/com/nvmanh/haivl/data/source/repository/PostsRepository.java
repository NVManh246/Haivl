package com.nvmanh.haivl.data.source.repository;

import com.nvmanh.haivl.data.source.OnCompleteListener;
import com.nvmanh.haivl.data.source.PostsDataSource;
import com.nvmanh.haivl.data.source.remote.PostsRemoteDataSource;

public class PostsRepository implements PostsDataSource.PostsRemoteDataSource {

    private static PostsRepository sIntance;
    private PostsDataSource.PostsRemoteDataSource mPostsRemoteDataSource;

    private PostsRepository(PostsRemoteDataSource postsRemoteDataSource) {
        mPostsRemoteDataSource = postsRemoteDataSource;
    }

    public static PostsRepository getIntance(PostsRemoteDataSource postsRemoteDataSource) {
        if(sIntance == null) {
            sIntance = new PostsRepository(postsRemoteDataSource);
        }
        return sIntance;
    }

    @Override
    public void getPosts(String myUsername, int page, OnCompleteListener callback) {
        mPostsRemoteDataSource.getPosts(myUsername, page, callback);
    }

    @Override
    public void like(String myUsername, int postId, boolean isLike) {
        mPostsRemoteDataSource.like(myUsername, postId, isLike);
    }
}
