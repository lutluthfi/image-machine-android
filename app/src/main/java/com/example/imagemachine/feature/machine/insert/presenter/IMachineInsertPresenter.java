package com.example.imagemachine.feature.machine.insert.presenter;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.imagemachine.feature.base.presenter.IBasePresenter;

public interface IMachineInsertPresenter extends IBasePresenter {
    void onButtonActionSaveClicked(@NonNull Bundle bundle);
}
