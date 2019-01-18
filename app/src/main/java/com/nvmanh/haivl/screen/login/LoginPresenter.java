package com.nvmanh.haivl.screen.login;

import android.os.Handler;
import android.text.TextUtils;

import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.User;
import com.nvmanh.haivl.data.source.OnCompleteListener;
import com.nvmanh.haivl.data.source.local.UsersLocalDataSource;
import com.nvmanh.haivl.data.source.remote.UsersRemoteDataSource;
import com.nvmanh.haivl.data.source.repository.UsersRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private UsersRepository mUsersRepository;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mUsersRepository = UsersRepository.getIntance(UsersRemoteDataSource.getIntance(),
                UsersLocalDataSource.getIntance());
    }

    @Override
    public void login(final String username, final String password) {
        if(TextUtils.isEmpty(username)) {
            mView.showError(mView.getContext().getResources().getString(R.string.required_enter_username));
            return;
        }
        if(TextUtils.isEmpty(password)){
            mView.showError(mView.getContext().getResources().getString(R.string.required_enter_password));
            return;
        }
        mView.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mUsersRepository.login(username, password, new OnCompleteListener<User>() {
                    @Override
                    public void onSuccess(User user) {
                        if (user.getUsername() != null) {
                            mView.navigateToMain(user);
                        } else {
                            mView.hideProgress();
                            mView.showError(mView.getContext().getResources()
                                    .getString(R.string.wrong_username_or_password));
                        }
                    }

                    @Override
                    public void onFail(Throwable throwable) {
                        mView.hideProgress();
                        mView.showError(throwable.getMessage());
                    }
                });
            }
        }, 1000);
    }
}
