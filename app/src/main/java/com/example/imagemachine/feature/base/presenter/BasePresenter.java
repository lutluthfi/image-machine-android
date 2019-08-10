package com.example.imagemachine.feature.base.presenter;

import androidx.annotation.NonNull;

import com.example.imagemachine.feature.base.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected V view;
    @NonNull
    protected Boolean isViewDidAttach = false;
    @NonNull
    protected CompositeDisposable compositeDisposable;

    public BasePresenter(V view) {
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void viewDidAttach() {
        this.isViewDidAttach = true;
    }

    @Override
    public void viewDidDetach() {
        this.compositeDisposable.dispose();
        this.compositeDisposable.clear();
        this.isViewDidAttach = false;
        this.view = null;
    }
}
