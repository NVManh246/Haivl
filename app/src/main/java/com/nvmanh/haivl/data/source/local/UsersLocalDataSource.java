package com.nvmanh.haivl.data.source.local;

import com.nvmanh.haivl.data.source.UsersDataSource;

public class UsersLocalDataSource implements UsersDataSource.UsersLocalDataSource {
    private static UsersLocalDataSource sIntance;

    private UsersLocalDataSource() {}

    public static UsersLocalDataSource getIntance() {
        if(sIntance == null) {
            sIntance = new UsersLocalDataSource();
        }
        return sIntance;
    }
}
