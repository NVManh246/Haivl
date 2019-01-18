package com.nvmanh.haivl.data.source.repository;

import com.nvmanh.haivl.data.source.OnCompleteListener;
import com.nvmanh.haivl.data.source.UsersDataSource;
import com.nvmanh.haivl.data.source.local.UsersLocalDataSource;
import com.nvmanh.haivl.data.source.remote.UsersRemoteDataSource;

public class UsersRepository implements UsersDataSource.UsersRemoteDataSource,
        UsersDataSource.UsersLocalDataSource {

    private static UsersRepository sIntance;
    private UsersDataSource.UsersRemoteDataSource mUsersRemoteDataSource;
    private UsersDataSource.UsersLocalDataSource mUsersLocalDataSource;

    private UsersRepository(UsersRemoteDataSource usersRemoteDataSource,
                            UsersLocalDataSource usersLocalDataSource) {
        mUsersRemoteDataSource = usersRemoteDataSource;
        mUsersLocalDataSource = usersLocalDataSource;
    }

    public static UsersRepository getIntance(UsersRemoteDataSource usersRemoteDataSource,
                                             UsersLocalDataSource usersLocalDataSource) {
        if(sIntance == null) {
            sIntance = new UsersRepository(usersRemoteDataSource, usersLocalDataSource);
        }
        return sIntance;
    }

    @Override
    public void login(String username, String password, OnCompleteListener callback) {
        mUsersRemoteDataSource.login(username, password, callback);
    }
}
