package com.example.studyguide_assoiateandroid.UserInterface.PagingEx;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.studyguide_assoiateandroid.AndroidCore.AndroidArchitecture.WordObject;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

public class PagingWordRepository {
    private static final String TAG = "PagingWordRepository";

    private PagingWordDao dao;
    public final LiveData<PagedList<PagingWordObject>>  words;
    private Context context;

    public PagingWordRepository(Application application) {
        context = application.getApplicationContext();
        PagingWordDatabase db = PagingWordDatabase.getInstance(application);
        dao = db.pagingWordDao();
        PagedList.Config myPagingConfig = new PagedList.Config.Builder()
                .setMaxSize(60)
                .setPrefetchDistance(20)
                .setPageSize(10)
                .build();

        // The Integer type argument corresponds to a PositionalDataSource object.
        DataSource.Factory<Integer, PagingWordObject> wordDataSource = dao.getWords();

        words = new LivePagedListBuilder<>(wordDataSource, myPagingConfig)
                        .build();
    }


    LiveData<PagedList<PagingWordObject>> getAllWords() {
        return words;
    }

    public void insert (PagingWordObject word){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(word);
            }
        }).start();
    }

    //***************************** volley ex *************************************************************************************************************
    public void fetchMoreWords(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://1000mostcommonwords.com/1000-most-common-english-words/";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                              //  textView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
