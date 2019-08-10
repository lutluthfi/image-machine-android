package com.example.imagemachine.feature.machine.detail.presenter;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.imagemachine.feature.base.presenter.IBasePresenter;

public interface IMachineDetailPresenter extends IBasePresenter {
    void onButtonActionSaveClicked(@NonNull Bundle bundle);
}
