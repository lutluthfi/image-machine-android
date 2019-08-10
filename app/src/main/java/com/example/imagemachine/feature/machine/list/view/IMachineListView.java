package com.example.imagemachine.feature.machine.list.view;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.base.view.IBaseView;

import java.util.List;

public interface IMachineListView extends IBaseView {
    void bindMachinesToRecycler(@NonNull List<Machine> machines);
    void goToMachineDetailActivity(@NonNull Bundle bundle);
    void onSuccessMachineRemove();
}
