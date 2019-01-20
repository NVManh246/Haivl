package com.nvmanh.haivl.screen.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.nvmanh.haivl.R;
import com.nvmanh.haivl.data.model.User;
import com.nvmanh.haivl.screen.home.HomeFragment;
import com.nvmanh.haivl.screen.hot.HotFragment;
import com.nvmanh.haivl.screen.library.LibraryFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_USER = "user";

    private User mUser;
    private HomeFragment mHomeFragment;
    private HotFragment mHotFragment;
    private LibraryFragment mLibraryFragment;
    private Fragment mFragmentCurrent;
    private FragmentManager mFragmentManager;
    private BottomNavigationView mNavigationView;

    public static Intent getMainIntent(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.bottom_navigaiont);
        mNavigationView.setOnNavigationItemSelectedListener(this);

        mUser = getUser();

        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_USER, mUser);
        mHomeFragment = new HomeFragment();
        mHomeFragment.setArguments(bundle);
        mHotFragment = new HotFragment();
        mLibraryFragment = new LibraryFragment();
        mFragmentManager = getSupportFragmentManager();

        mFragmentManager.beginTransaction()
                .add(R.id.frame_content, mHomeFragment)
                .add(R.id.frame_content, mHotFragment)
                .add(R.id.frame_content, mLibraryFragment)
                .hide(mHotFragment)
                .hide(mLibraryFragment)
                .show(mHomeFragment)
                .commit();
        mFragmentCurrent = mHomeFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                showFragment(mHomeFragment);
                break;
            case R.id.menu_hot:
                showFragment(mHotFragment);
                break;
            case R.id.menu_library:
                showFragment(mLibraryFragment);
                break;
        }
        return true;
    }

    private void showFragment(Fragment fShow) {
        mFragmentManager.beginTransaction()
                .hide(mFragmentCurrent)
                .show(fShow)
                .commit();
        mFragmentCurrent = fShow;
    }

    private User getUser() {
        Intent intent = getIntent();
        User user = intent.getParcelableExtra(EXTRA_USER);
        return user;
    }
}
