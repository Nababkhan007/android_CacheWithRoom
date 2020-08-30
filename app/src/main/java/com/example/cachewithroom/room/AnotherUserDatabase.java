package com.example.cachewithroom.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = AnotherUser.class, version = 8)
public abstract class AnotherUserDatabase extends RoomDatabase {
    public abstract AnotherUserDao anotherUserDao();

    private static volatile AnotherUserDatabase anotherUserDatabase;

    public static AnotherUserDatabase getAnotherUserDatabase(Context context) {
        if (anotherUserDatabase == null) {
            synchronized (AnotherUserDatabase.class) {
                if (anotherUserDatabase == null) {
                    anotherUserDatabase = Room
                            .databaseBuilder(context, AnotherUserDatabase.class, "another_user_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return anotherUserDatabase;
    }
}
