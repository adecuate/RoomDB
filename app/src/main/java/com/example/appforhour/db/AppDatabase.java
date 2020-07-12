package com.example.appforhour.db;

import com.example.appforhour.entity.Word;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDAO getWordDAO();
}
