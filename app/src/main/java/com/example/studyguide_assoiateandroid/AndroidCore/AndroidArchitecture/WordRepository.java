package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<WordObject>> allWords;


    WordRepository(Application application) {
        WordDataBase db = WordDataBase.getInstance(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAllWords();
    }

    LiveData<List<WordObject>> getAllWords() {
        return allWords;
    }

//    public void insert (WordObject word){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                wordDao.insert(word);
//            }
//        }).start();
//    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(WordObject word) {
        WordDataBase.databaseWriteExecutor.execute(() -> {
            wordDao.insert(word);
        });
    }

public void deleteWord(WordObject wordObject){
        new Thread(new Runnable() {
            @Override
            public void run() {
                wordDao.deleteWord(wordObject);
            }
        }).start();
}
}
