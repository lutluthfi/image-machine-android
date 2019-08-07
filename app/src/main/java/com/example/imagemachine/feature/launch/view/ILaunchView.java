package com.example.imagemachine.feature.launch.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.imagemachine.feature.base.view.IBaseView;

public interface ILaunchView extends IBaseView {
    void goToMainActivity(@NonNull Bundle bundle);
}
