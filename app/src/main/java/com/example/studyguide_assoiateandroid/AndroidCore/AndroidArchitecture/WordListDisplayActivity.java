package com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.studyguide_assoiateandroid.R;

import java.util.List;

public class WordListDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WordRecyclerViewAdapter adapter;

    private WordViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list_display);


        // get view model from viewModelProviders
        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        viewModel.getAllWords().observe(this, new Observer<List<WordObject>>() {
            @Override
            public void onChanged(List<WordObject> wordObjects) {
                initiateRecycler(wordObjects);
            }
        });

    }

    private void initiateRecycler(List<WordObject> words){
        recyclerView = findViewById(R.id.shrine_container);
        adapter = new WordRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords(words);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                WordObject word = adapter.deleteWord(position);
                viewModel.deleteWord(word);
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }
}