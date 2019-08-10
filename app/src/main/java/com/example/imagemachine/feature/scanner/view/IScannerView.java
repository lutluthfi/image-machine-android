package com.example.imagemachine.feature.scanner.view;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.imagemachine.feature.base.view.IBaseView;

public interface IScannerView extends IBaseView {
    void goToMachineInsertActivity(@NonNull Bundle bundle);
}
