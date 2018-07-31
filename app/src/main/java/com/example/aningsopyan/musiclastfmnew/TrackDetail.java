package com.example.aningsopyan.musiclastfmnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.aningsopyan.musiclastfmnew.adapter.AdapterTrack;
import com.example.aningsopyan.musiclastfmnew.albumtrack.AlbumTrack;
import com.example.aningsopyan.musiclastfmnew.albumtrack.Track;
import com.example.aningsopyan.musiclastfmnew.service.TrackDetailInterface;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrackDetail extends AppCompatActivity {

    AdapterTrack adapter;
    RecyclerView recyclerView;
    private String album_name;
    String image_album;
    ImageView imageView,music_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);
        Intent intent = getIntent();
        imageView = findViewById(R.id.toolbarImage);
        music_logo = findViewById(R.id.music_logo);
        album_name = intent.getExtras().getString("album_name");
        image_album = intent.getExtras().getString("image_album");
        recyclerView = findViewById(R.id.rv_track);
        recyclerView.setLayoutManager(new LinearLayoutManager(TrackDetail.this));


        Picasso.with(this)
                .load(image_album)
                .into(imageView);


        //create retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TrackDetailInterface service = retrofit.create(TrackDetailInterface.class);
        Call<AlbumTrack> call = service.getAlbumTrack(album_name);
        call.enqueue(new Callback<AlbumTrack>() {
            @Override
            public void onResponse(Call<AlbumTrack> call, Response<AlbumTrack> response) {
                List<Track> results = response.body().getAlbum().getTracks().getTrack();
                int responsecode = response.code();
                Log.i("response", String.valueOf(responsecode));
                Log.i("json_track", new GsonBuilder().setPrettyPrinting().create().toJson(results));
                Log.i("name_album_main",String.valueOf(album_name));

                adapter = new AdapterTrack(results,R.layout.list_track_album, getApplicationContext());

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AlbumTrack> call, Throwable t) {

            }
        });
    }

    public void toolbaranimation(View view){
        final Animation blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        imageView.startAnimation(blink);
    }
}
