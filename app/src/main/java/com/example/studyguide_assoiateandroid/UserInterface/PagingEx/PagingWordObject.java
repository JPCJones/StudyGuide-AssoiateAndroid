package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "paging_word_table")
public class PagingWordObject {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    private String word;

    public PagingWordObject(String word) {
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

    @Ignore
    @Override
    public String toString() {
        return "PagingWordObject{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }

    @Ignore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagingWordObject that = (PagingWordObject) o;
        return id == that.id &&
                Objects.equals(word, that.word);
    }

    @Ignore
    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }
}
