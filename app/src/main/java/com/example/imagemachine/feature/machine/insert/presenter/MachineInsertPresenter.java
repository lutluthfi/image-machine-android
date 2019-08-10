package com.example.imagemachine.feature.machine.insert.presenter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.imagemachine.data.model.LocalMachineDataSource;
import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.machine.insert.view.IMachineInsertView;
import com.example.imagemachine.utils.Constant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MachineInsertPresenter<V extends IMachineInsertView> extends BasePresenter<V>
        implements IMachineInsertPresenter {

    //
    // MARK: - Dependencies
    //
    private final LocalMachineDataSource machineDataSource;

    public MachineInsertPresenter(V view, LocalMachineDataSource machineDataSource) {
        super(view);
        this.machineDataSource = machineDataSource;
    }

    //
    // MARK: - Override Function of BasePresenter
    //
    @Override
    public void viewDidAttach() {
        super.viewDidAttach();
    }

    @Override
    public void viewDidDetach() {
        super.viewDidDetach();
    }

    //
    // MARK: - Override Function of IMachineInsertPresenter
    //
    @Override
    public void onButtonActionSaveClicked(@NonNull Bundle bundle) {
        if (this.view != null) {

            String name = bundle.getString(Constant.KEY_NAME);
            String type = bundle.getString(Constant.KEY_TYPE);
            String qrCode = bundle.getString(Constant.KEY_BARCODE_VALUE);
            String date = bundle.getString(Constant.KEY_DATE);

            Machine machine = new Machine();
            machine.setName(name);
            machine.setType(type);
            machine.setQrCode(qrCode);
            machine.setDate(date);

            this.compositeDisposable.add(this.machineDataSource.insert(machine)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        this.view.onSuccessInsertMachineData();
                    }, throwable -> {
                        Log.d("MachineInsertPresenter", throwable.getMessage());
                    }));
        }
    }
}
