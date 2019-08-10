package com.example.imagemachine.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.data.model.MachineDao;

@Database(entities = {Machine.class}, version = 1, exportSchema = false)
public abstract class RoomModule extends RoomDatabase {

    public abstract MachineDao machineDao();

    private static volatile RoomModule INSTANCE;

    public static RoomModule getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (RoomModule.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomModule.class, "machine.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
