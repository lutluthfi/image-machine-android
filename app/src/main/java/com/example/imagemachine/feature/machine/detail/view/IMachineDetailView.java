package com.example.imagemachine.feature.machine.detail.view;

import com.example.imagemachine.feature.base.view.IBaseView;

public interface IMachineDetailView extends IBaseView {
    void goToMainActivity();
    void onSuccessInsertMachineData();
    void onSuccessUpdateMachineData();
}
