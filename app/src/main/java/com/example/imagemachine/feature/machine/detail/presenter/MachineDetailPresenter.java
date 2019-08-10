package com.example.imagemachine.feature.machine.detail.presenter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.imagemachine.data.model.LocalMachineDataSource;
import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.machine.detail.view.IMachineDetailView;
import com.example.imagemachine.utils.Constant;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MachineDetailPresenter<V extends IMachineDetailView> extends BasePresenter<V>
        implements IMachineDetailPresenter {

    //
    // MARK: - Dependencies
    //
    private final LocalMachineDataSource machineDataSource;

    public MachineDetailPresenter(V view, LocalMachineDataSource machineDataSource) {
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
    // MARK: - Override Function of IMachineDetailPresenter
    //
    @Override
    public void onButtonActionSaveClicked(@NotNull Bundle bundle) {
        if (this.view != null) {

            int id = bundle.getInt(Constant.KEY_ID);
            String name = bundle.getString(Constant.KEY_NAME);
            String type = bundle.getString(Constant.KEY_TYPE);
            String qrCode = bundle.getString(Constant.KEY_BARCODE_VALUE);
            String image = bundle.getString(Constant.KEY_IMAGE);
            String date = bundle.getString(Constant.KEY_DATE);
            String queryRoom = bundle.getString(Constant.KEY_QUERY_ROOM);

            Machine machine = new Machine();
            machine.setName(name);
            machine.setType(type);
            machine.setQrCode(qrCode);
            machine.setImage(image);
            machine.setDate(date);

           switch (queryRoom) {
               case "insert":
                   this.compositeDisposable.add(this.machineDataSource
                           .insert(machine)
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(() -> {
                               this.view.onSuccessInsertMachineData();
                           }, throwable -> {
                               Log.d("MachineDetailPresenter", throwable.getMessage());
                           }));
                   break;
               case "update":
                   this.compositeDisposable.add(this.machineDataSource
                           .update(id, name, type, qrCode, image, date)
                           .subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(() -> {
                               this.view.onSuccessUpdateMachineData();
                           }, throwable -> {
                               Log.d("MachineDetailPresenter", throwable.getMessage());
                           }));
                   break;
           }
        }
    }
}
