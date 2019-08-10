package com.example.imagemachine.data.model;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalMachineDataSource implements MachineDao {

    private final MachineDao dao;

    public LocalMachineDataSource(@NonNull MachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<Machine>> fetchMachinesAll() {
        return this.dao.fetchMachinesAll();
    }

    @Override
    public Flowable<List<Machine>> fetchMachinesAllSortByName() {
        return this.dao.fetchMachinesAllSortByName();
    }

    @Override
    public Flowable<List<Machine>> fetchMachinesAllSortByType() {
        return this.dao.fetchMachinesAllSortByType();
    }

    @Override
    public Flowable<Machine> fetchMachine(@NotNull int id) {
        return this.dao.fetchMachine(id);
    }

    @Override
    public int count() {
        return this.dao.count();
    }

    @Override
    public Completable insert(@NotNull Machine machine) {
        return this.dao.insert(machine);
    }

    @Override
    public Completable delete(@NotNull Machine machine) {
        return this.dao.delete(machine);
    }

}
