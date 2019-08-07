package com.example.imagemachine.feature.base.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.imagemachine.utils.LoadingView;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @NonNull
    private LoadingView loadingView = new LoadingView(this);

    @Override
    public void dismissLoadingView() {
        runOnUiThread(() -> {
            if (this.loadingView.isLoadingDialogDidShow()) {
                this.loadingView.dismissLoadingView();
            }
        });
    }

    @Override
    public Boolean isLoadingViewDidShow() {
        return this.loadingView.isLoadingDialogDidShow();
    }

    @Override
    public void showLoadingView(@NonNull String message) {
        runOnUiThread(() -> {
            if (this.loadingView.isLoadingDialogDidShow()) {
                this.loadingView.dismissLoadingView();
            }
            this.loadingView.showLoadingView(message);
        });
    }

}
