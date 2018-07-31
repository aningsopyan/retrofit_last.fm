package com.example.aningsopyan.musiclastfmnew.service;

import com.example.aningsopyan.musiclastfmnew.model.ArtisTopAlbum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/2.0/?method=artist.gettopalbums&artist=radiohead&format=json")
    Call<ArtisTopAlbum> getResult (@Query("api_key") String apiKey);

}
