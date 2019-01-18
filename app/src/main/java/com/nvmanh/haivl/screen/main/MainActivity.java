package com.nvmanh.haivl.screen.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.User;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "user";

    public static Intent getMainIntent(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
