package com.nvmanh.haivl.screen.home;

import com.nvmanh.haivl.data.model.Post;
import com.nvmanh.haivl.data.source.OnCompleteListener;
import com.nvmanh.haivl.data.source.remote.PostsRemoteDataSource;
import com.nvmanh.haivl.data.source.repository.PostsRepository;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private PostsRepository mPostsRepository;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mPostsRepository = PostsRepository.getIntance(PostsRemoteDataSource.getIntance());
    }

    @Override
    public void getPost(String myUsername) {
        mPostsRepository.getPosts(myUsername, new OnCompleteListener<List<Post>>() {
            @Override
            public void onSuccess(List<Post> posts) {
                mView.showPosts(posts);
            }

            @Override
            public void onFail(Throwable throwable) {
                mView.showError(throwable.getMessage());
            }
        });
    }
}
