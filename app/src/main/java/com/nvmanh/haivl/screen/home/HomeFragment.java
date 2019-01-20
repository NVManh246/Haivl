package com.nvmanh.haivl.screen.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.Post;
import com.nvmanh.haivl.data.model.User;
import com.nvmanh.haivl.screen.main.MainActivity;
import com.nvmanh.haivl.utils.EndLessScrollListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View, PostAdapter.OnLikeListener {

    private RecyclerView mRecyclerPost;
    private PostAdapter mPostAdapter;
    private List<Post> mPosts;
    private User mUser;
    private int mPage;
    private HomeContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomePresenter(this);
        mRecyclerPost = view.findViewById(R.id.recycler_post);

        mUser = (User) getArguments().get(MainActivity.EXTRA_USER);

        mPosts = new ArrayList<>();
        mPostAdapter = new PostAdapter(getContext(), mPosts, this);
        mRecyclerPost.setAdapter(mPostAdapter);
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerPost.addItemDecoration(new PostItemDecoration(10));
        mPresenter.getPost(mUser.getUsername(), mPage);
        mRecyclerPost.addOnScrollListener(new EndLessScrollListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getPost(mUser.getUsername(), ++mPage);
            }
        });
    }

    @Override
    public void showPosts(List<Post> posts) {
        mPostAdapter.addData(posts);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onLike(int postId, boolean isLike) {
        mPresenter.like(mUser.getUsername(), postId, isLike);
    }
}
