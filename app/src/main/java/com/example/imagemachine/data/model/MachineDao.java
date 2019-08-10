package com.example.imagemachine.data.model;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MachineDao {
    @Query(value = "SELECT * FROM machine")
    Flowable<List<Machine>> fetchMachinesAll();
    @Query(value = "SELECT * FROM machine WHERE id = :id LIMIT 1")
    Flowable<Machine> fetchMachine(@NonNull int id);
    @Query(value = "SELECT COUNT(*) from machine")
    int count();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(@NonNull Machine machine);
    @Delete
    Completable delete(@NonNull Machine machine);
}
