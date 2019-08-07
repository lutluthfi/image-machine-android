package com.example.imagemachine.feature.base.presenter;

import android.support.annotation.NonNull;

import com.example.imagemachine.feature.base.view.IBaseView;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected V view;
    @NonNull
    protected Boolean isViewDidAttach = false;

    public BasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void viewDidAttach() {
        this.isViewDidAttach = true;
    }

    @Override
    public void viewDidDetach() {
        this.isViewDidAttach = false;
        this.view = null;
    }
}
