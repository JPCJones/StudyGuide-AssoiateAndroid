package com.example.studyguide_assoiateandroid.UserInterface.PagingEx.Retrofit;

import com.example.studyguide_assoiateandroid.UserInterface.PagingEx.PagingWordObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostEndPoint {

    @GET
    Call<List<PagingWordObject>> getWordPosts();


}
