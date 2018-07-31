package com.example.aningsopyan.musiclastfmnew.service;

import com.example.aningsopyan.musiclastfmnew.albumtrack.AlbumTrack;
import com.example.aningsopyan.musiclastfmnew.model.ArtisTopAlbum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrackDetailInterface {

    @GET("2.0/?method=album.getinfo&api_key=f0b0da7401a59224396fc7ced3537cb3&artist=Radiohead&format=json")
    Call<AlbumTrack> getAlbumTrack(@Query("album") String album_name);
}
