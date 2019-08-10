package com.example.imagemachine.data.model;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MachineDao {
    @Query(value = "SELECT * FROM machine")
    Flowable<List<Machine>> fetchMachinesAll();
    @Query(value = "SELECT :id FROM machine LIMIT 1")
    Flowable<Machine> fetchMachine(@NonNull Integer id);
    @Query(value = "SELECT COUNT(*) from machine")
    int count();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(@NonNull Machine machine);
    @Delete
    void delete(@NonNull Machine machine);
}
