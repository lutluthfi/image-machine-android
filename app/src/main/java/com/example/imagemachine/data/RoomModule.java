package com.example.imagemachine.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.imagemachine.data.model.Machine;
import com.example.imagemachine.data.model.MachineDao;

@Database(entities = {Machine.class}, version = 2, exportSchema = false)
public abstract class RoomModule extends RoomDatabase {

    public abstract MachineDao machineDao();

    private static volatile RoomModule INSTANCE;

    public static RoomModule getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (RoomModule.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomModule.class, "machine.db")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE machine ADD COLUMN image TEXT");
        }
    };
}
