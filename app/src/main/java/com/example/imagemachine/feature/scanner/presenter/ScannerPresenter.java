package com.example.imagemachine.feature.scanner.presenter;

import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.base.presenter.IBasePresenter;
import com.example.imagemachine.feature.scanner.view.IScannerView;

public class ScannerPresenter<V extends IScannerView> extends BasePresenter<V> implements IBasePresenter {

    public ScannerPresenter(V view) {
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
