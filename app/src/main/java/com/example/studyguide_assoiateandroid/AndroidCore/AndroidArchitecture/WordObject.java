package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class WordObject {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String word;

    public WordObject(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
