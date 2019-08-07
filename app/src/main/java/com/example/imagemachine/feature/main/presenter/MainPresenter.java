package com.example.imagemachine.feature.main.presenter;

import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.main.view.IMainView;

public class MainPresenter<V extends IMainView> extends BasePresenter<V> implements IMainPresenter {

    public MainPresenter(V view) {
        super(view);
    }

    @Override
    public void viewDidAttach() {
        super.viewDidAttach();
    }

    @Override
    public void viewDidDetach() {
        super.viewDidDetach();
    }
}
