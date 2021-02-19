package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PagingWordDao {

    @Insert
    void insert(PagingWordObject word);

    @Insert
    void insertMany(List<PagingWordObject> words);

    @Query("SELECT * FROM paging_word_table")
    DataSource.Factory<Integer, PagingWordObject> getWords();

    @Query("SELECT COUNT(id) FROM paging_word_table")
    int getWordCount();
}
