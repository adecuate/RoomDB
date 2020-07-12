package com.example.appforhour.entity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey
    @NonNull
    private String englishVer;
    private String rusVer;

    public Word(String englishVer, String rusVer) {
        this.englishVer = englishVer;
        this.rusVer = rusVer;
    }

    public String getEnglishVer() {
        return englishVer;
    }

    public String getRusVer() {
        return rusVer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return englishVer.equals(word.englishVer) &&
                rusVer.equals(word.rusVer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishVer, rusVer);
    }




}
