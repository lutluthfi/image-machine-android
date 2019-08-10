package com.example.imagemachine.feature.machine.insert.presenter;

import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.machine.insert.view.IMachineInsertView;

public class MachineInsertPresenter<V extends IMachineInsertView> extends BasePresenter<V> implements IMachineInsertPresenter {

    public MachineInsertPresenter(V view) {
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
