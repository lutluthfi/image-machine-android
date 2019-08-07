package com.example.imagemachine.feature.base.view;

import android.support.annotation.NonNull;

public interface IBaseView {

    void dismissLoadingView();

    Boolean isLoadingViewDidShow();

    void showLoadingView(@NonNull String message);

}
