package com.example.appforhour.db;

import com.example.appforhour.entity.Word;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDAO {
    @Insert
    void insert(Word word);

    @Query("Select * FROM Word")
    List<Word> getWords();
}
