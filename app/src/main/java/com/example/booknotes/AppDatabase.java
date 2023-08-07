package com.example.booknotes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static AppDatabase instance;
    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "books.db"
                )
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE books ADD COLUMN time TEXT NOT NULL DEFAULT ''");
        }
    };
}
