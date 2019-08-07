package com.example.imagemachine.feature.launch.presenter;

import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.launch.view.ILaunchView;

public class LaunchPresenter<V extends ILaunchView> extends BasePresenter<V> implements ILaunchPresenter {

    public LaunchPresenter(V view) {
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
