package com.example.imagemachine.feature.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.imagemachine.R;
import com.example.imagemachine.feature.base.view.BaseActivity;
import com.example.imagemachine.feature.main.presenter.IMainPresenter;
import com.example.imagemachine.feature.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements IMainView {

    //
    // MARK: - Dependencies
    //
    private IMainPresenter presenter;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter<IMainView>(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.viewDidAttach();
    }

    @Override
    protected void onDestroy() {
        this.presenter.viewDidDetach();
        super.onDestroy();
    }

    //
    // MARK: - Function Static
    //
    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }
}
