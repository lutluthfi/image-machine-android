package com.example.imagemachine.data.model;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface MachineDao {
    @Query(value = "SELECT * FROM machine")
    Flowable<List<Machine>> fetchMachinesAll();

    @Query(value = "SELECT * FROM machine ORDER BY name ASC")
    Flowable<List<Machine>> fetchMachinesAllSortByName();

    @Query(value = "SELECT * FROM machine ORDER BY type ASC")
    Flowable<List<Machine>> fetchMachinesAllSortByType();

    @Query(value = "SELECT * FROM machine WHERE id = :id LIMIT 1")
    Flowable<Machine> fetchMachine(@NonNull int id);

    @Query(value = "SELECT COUNT(*) from machine")
    int count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(@NonNull Machine machine);

    @Query(value = "UPDATE machine" +
            " SET name = :name, type = :type, qrCode = :qrCode, image = :image, date = :date" +
            " WHERE id = :id")
    Completable update(int id,
                       @NonNull String name,
                       @NonNull String type,
                       @NonNull String qrCode,
                       @NonNull String image,
                       @NonNull String date);

    @Delete
    Completable delete(@NonNull Machine machine);
}
