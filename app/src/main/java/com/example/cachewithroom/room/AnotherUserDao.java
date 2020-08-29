package com.example.cachewithroom.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnotherUserDao {
    @Insert
    void insert(AnotherUser anotherUser);

    @Query("SELECT * FROM another_users")
    List<AnotherUser> getAllAnotherUserList();
}
