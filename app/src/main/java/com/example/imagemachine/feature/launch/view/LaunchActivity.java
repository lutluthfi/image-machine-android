package com.example.imagemachine.feature.launch.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.launch.presenter.ILaunchPresenter;
import com.example.imagemachine.feature.launch.presenter.LaunchPresenter;
import com.example.imagemachine.feature.main.view.MainActivity;

public class LaunchActivity extends BaseActivity implements ILaunchView {

    //
    // MARK: - Dependencies
    //
    private ILaunchPresenter presenter;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        this.presenter = new LaunchPresenter<ILaunchView>(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.viewDidAttach();
        new Handler().postDelayed(() -> goToMainActivity(new Bundle()), DELAY);
    }

    @Override
    protected void onDestroy() {
        this.presenter.viewDidDetach();
        super.onDestroy();
    }

    //
    // MARK: - Override Function of ILaunchView
    //
    @Override
    public void goToMainActivity(@NonNull Bundle bundle) {
        startActivity(MainActivity.startIntent(this));
        finish();
    }

    //
    // MARK: - Variable Static
    //
    static final Long DELAY = 2000L;
}
