package com.nvmanh.haivl.data.source;

public interface UsersDataSource {
    interface UsersRemoteDataSource {
        void login(String username, String password, OnCompleteListener callback);
    }

    interface UsersLocalDataSource {

    }
}
