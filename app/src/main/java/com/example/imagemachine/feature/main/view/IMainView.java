package com.example.imagemachine.feature.main.view;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.imagemachine.feature.base.view.IBaseView;

public interface IMainView extends IBaseView {
    void goToMachineListActivity(@NonNull Bundle bundle);
    void goToScannerActivity(@NonNull Bundle bundle);
}
