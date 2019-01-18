package com.nvmanh.haivl.screen.login;

import android.content.Context;

import com.nvmanh.haivl.data.model.User;

public interface LoginContract {
    interface View {
        void showProgress();
        void hideProgress();
        void navigateToMain(User user);
        void showError(String error);
        Context getContext();
    }

    interface Presenter {
        void login(String username, String password);
    }
}
