package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {


    @Insert
    void insert(WordObject word);

    @Delete
    void deleteWord(WordObject word);

    @Query("SELECT * FROM word_table ORDER by word ASC")
    LiveData<List<WordObject>> getAllWords();


    /*
    @Query("SELECT * from word_table ORDER BY word ASC")
    @Query("DELETE FROM word_table")
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     */


}
