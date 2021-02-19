package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;

public class PagingViewModel extends AndroidViewModel {

    private PagingWordRepository repository;
    public final LiveData<PagedList<PagingWordObject>> words;

    public PagingViewModel(@NonNull Application application) {
        super(application);
        repository = new PagingWordRepository(application);
        words = repository.getAllWords();
    }

    LiveData<PagedList<PagingWordObject>> getAllWords() {
        return words;
    }
}
