package com.example.imagemachine.data.model;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;

public class LocalMachineDataSource implements MachineDao {

    private final MachineDao dao;

    public LocalMachineDataSource(MachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<Machine>> fetchMachinesAll() {
        return this.dao.fetchMachinesAll();
    }

    @Override
    public Flowable<Machine> fetchMachine(@NonNull Integer id) {
        return this.dao.fetchMachine(id);
    }

    @Override
    public int count() {
        return this.dao.count();
    }

    @Override
    public void insert(@NonNull Machine machine) {
        this.dao.insert(machine);
    }

    @Override
    public void delete(@NonNull Machine machine) {
        this.dao.delete(machine);
    }

}
