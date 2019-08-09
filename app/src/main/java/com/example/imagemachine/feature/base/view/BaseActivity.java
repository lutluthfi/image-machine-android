package com.example.imagemachine.feature.base.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imagemachine.utils.LoadingView;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    //
    // MARK: - Dependencies
    //
    private LoadingView loadingView;

    //
    // MARK: - Override Function of Activity Lifecycle
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadingView = new LoadingView(this);
    }

    //
    // MARK: - Override Function of IBaseView
    //
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

    //
    // MARK: - Abstract Function
    //
    protected abstract void preparingListener();

    protected abstract void preparingView();

    //
    // MARK: - Function Static
    //
    public static int REQUEST_ID_PERMISSION_CAMERA = 1005;
}
