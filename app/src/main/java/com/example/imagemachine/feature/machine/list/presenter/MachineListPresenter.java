package com.example.imagemachine.feature.machine.list.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.imagemachine.data.model.LocalMachineDataSource;
import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.feature.base.presenter.BasePresenter;
import com.example.imagemachine.feature.machine.list.view.IMachineListView;

import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MachineListPresenter<V extends IMachineListView> extends BasePresenter<V> implements
        IMachineListPresenter {

    //
    // MARK: - Dependencies
    //
    private final LocalMachineDataSource machineDataSource;

    public MachineListPresenter(V view, LocalMachineDataSource machineDataSource) {
        super(view);
        this.machineDataSource = machineDataSource;
    }

    @Override
    public void viewDidAttach() {
        if (!this.isViewDidAttach) {
            if (this.view != null) {
                this.compositeDisposable.add(this.machineDataSource.fetchMachinesAll()
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(machines -> {
                            this.view.bindMachinesToRecycler(machines);
                        }, throwable -> {
                            Log.d("MachineListPresenter", throwable.getMessage());
                        }));
            }
        }
        super.viewDidAttach();
    }

    @Override
    public void viewDidDetach() {
        super.viewDidDetach();
    }

    @Override
    public void onMachineRemoveClicked(@NonNull Machine machine) {
        if (this.view != null) {
            this.compositeDisposable.add(this.machineDataSource.delete(machine)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        this.view.onSuccessMachineRemove();
                    }, throwable -> {
                        Log.d("MachineListPresenter", throwable.getMessage());
                    }));
        }
    }

    @Override
    public void onMachineSortClicked(@NonNull String column) {
        if (this.view != null) {
            switch (column) {
                case "name":
                    this.compositeDisposable.add(this.machineDataSource
                            .fetchMachinesAllSortByName()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(machines -> {
                                this.view.bindMachinesToRecycler(machines);
                            }, throwable -> {
                                Log.d("MachineListPresenter", throwable.getMessage());
                            }));
                    break;
                case "type":
                    this.compositeDisposable.add(this.machineDataSource
                            .fetchMachinesAllSortByType()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(machines -> {
                                this.view.bindMachinesToRecycler(machines);
                            }, throwable -> {
                                Log.d("MachineListPresenter", throwable.getMessage());
                            }));
                    break;
            }
        }
    }
}
