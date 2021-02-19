package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//************************ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! *************************************
//Do not store Activity, Fragment, or View instances or their Context in the ViewModel.
public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<List<WordObject>> allWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
        allWords = repository.getAllWords();
    }

    LiveData<List<WordObject>> getAllWords() { return allWords; }

    public void insert(WordObject word) { repository.insert(word); }

    public void deleteWord(WordObject word) {repository.deleteWord(word);}
}
