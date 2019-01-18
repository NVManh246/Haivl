package com.nvmanh.haivl.screen.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.User;
import com.nvmanh.haivl.screen.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        LoginContract.View {

    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private FrameLayout mFrameProgress;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.button_login).setOnClickListener(this);
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mFrameProgress = findViewById(R.id.frame_progress);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                String username = mEditTextUsername.getText().toString();
                String password = mEditTextPassword.getText().toString();
                mPresenter.login(username, password);
                break;
        }
    }

    @Override
    public void showProgress() {
        mFrameProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mFrameProgress.setVisibility(View.GONE);
    }

    @Override
    public void navigateToMain(User user) {
        Intent intent = MainActivity.getMainIntent(this, user);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
