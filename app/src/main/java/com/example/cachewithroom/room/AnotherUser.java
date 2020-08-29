package com.example.cachewithroom.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "another_users")
public class AnotherUser {
    @NonNull
    @PrimaryKey
    private String id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public AnotherUser(String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
