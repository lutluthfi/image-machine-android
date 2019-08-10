package com.example.imagemachine.feature.machine.list.presenter;

import androidx.annotation.NonNull;

import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.base.presenter.IBasePresenter;

public interface IMachineListPresenter extends IBasePresenter {
    void onMachineRemoveClicked(@NonNull Machine machine);
}
