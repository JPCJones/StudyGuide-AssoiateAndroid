package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordObject;
import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordRecyclerViewAdapter;
import com.example.studyguide_assoiateandroid.R;

import java.util.List;

public class PagingExample extends AppCompatActivity {
    private static final String TAG = "PagingExample";
/* add to gradle
 implementation "androidx.paging:paging-runtime:$paging_version" // For Kotlin use paging-runtime-ktx

  // alternatively - without Android dependencies for testing
  testImplementation "androidx.paging:paging-common:$paging_version" // For Kotlin use paging-common-ktx

  // optional - RxJava support
  implementation "androidx.paging:paging-rxjava2:$paging_version" // For Kotlin use paging-rxjava2-ktx
 */

    private PagingViewModel viewModel;

    private RecyclerView recyclerView;
    private PagingRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_example);

        viewModel = ViewModelProviders.of(this).get(PagingViewModel.class);

        recyclerView = findViewById(R.id.paging_recyclerView);
        adapter = new PagingRecyclerViewAdapter();
        adapter.setContext(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // sets the items of the adapter for you and observes the change
        viewModel.getAllWords().observe(this, adapter::submitList);
        recyclerView.setAdapter(adapter);

    }

    private void initiateRecycler(List<PagingWordObject> words){
        recyclerView = findViewById(R.id.paging_recyclerView);
        adapter = new PagingRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter.setWords(words);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                WordObject word = adapter.deleteWord(position);
//                viewModel.deleteWord(word);
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }
}