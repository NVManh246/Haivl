package com.nvmanh.haivl.screen.home;

import android.content.Context;

import com.nvmanh.haivl.data.model.Post;

import java.util.List;

public interface HomeContract {
    interface View {
        void showPosts(List<Post> posts);
        void showError(String error);
        Context getContext();
    }

    interface Presenter {
        void getPost(String myUsername);
        void like(String username, int postId, boolean isLike);
    }
}
