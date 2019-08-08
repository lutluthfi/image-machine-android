package com.example.imagemachine.feature.base.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.imagemachine.utils.LoadingView;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private LoadingView loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadingView = new LoadingView(this);
    }

    @Override
    public void dismissLoadingView() {
        runOnUiThread(() -> {
            if (this.loadingView.isLoadingViewDidShow()) {
                this.loadingView.dismissLoadingView();
            }
        });
    }

    @Override
    public Boolean isLoadingViewDidShow() {
        return this.loadingView.isLoadingViewDidShow();
    }

    @Override
    public void showLoadingView(@NonNull String message) {
        runOnUiThread(() -> {
            if (this.loadingView.isLoadingViewDidShow()) {
                this.loadingView.dismissLoadingView();
            }
            this.loadingView.showLoadingView(message);
        });
    }

}
