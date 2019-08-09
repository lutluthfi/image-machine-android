package com.example.imagemachine.feature.machine.list.presenter;

import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.machine.list.view.IMachineListView;

public class MachineListPresenter<V extends IMachineListView> extends BasePresenter<V> implements IMachineListPresenter {

    public MachineListPresenter(V view) {
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
