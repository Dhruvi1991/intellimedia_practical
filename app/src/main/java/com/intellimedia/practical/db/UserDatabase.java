package com.intellimedia.practical.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.intellimedia.practical.auth.model.UserTable;

@Database(entities = {UserTable.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public static UserDatabase INSTANCE;

    public abstract UserDao userDao();

    public static UserDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (UserDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context, UserDatabase.class, "USER_DATABASE").createFromAsset("database/USER_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }

        }

        return INSTANCE;

    }
}
