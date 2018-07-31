package com.example.aningsopyan.musiclastfmnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.aningsopyan.musiclastfmnew.adapter.Adapter;
import com.example.aningsopyan.musiclastfmnew.model.Album;
import com.example.aningsopyan.musiclastfmnew.model.ArtisTopAlbum;
import com.example.aningsopyan.musiclastfmnew.service.ApiInterface;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    private Adapter adapter;
    private final static String API_KEY = "f0b0da7401a59224396fc7ced3537cb3";
    RecyclerView recyclerView;
    SearchView searchView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        searchView.setFocusable(false);
        linearLayout = findViewById(R.id.linear_item);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<ArtisTopAlbum> result = service.getResult(API_KEY);
        result.enqueue(new Callback<ArtisTopAlbum>() {
            @Override
            public void onResponse(Call<ArtisTopAlbum> call, Response<ArtisTopAlbum> response) {

                List<Album> results = response.body().getTopalbums().getAlbum();
                int responsecode = response.code();
                Log.i("response", String.valueOf(responsecode));
                Log.i("json code ", new GsonBuilder().setPrettyPrinting().create().toJson(results));

                adapter = new Adapter(results,R.layout.list_item, getApplicationContext());

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ArtisTopAlbum> call, Throwable t) {

                Log.e("failure", String.valueOf(t));
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if (!s.equals("")){

            adapter.filter(s);
        }

        else if (s.equals("")){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ws.audioscrobbler.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface service = retrofit.create(ApiInterface.class);
            Call<ArtisTopAlbum> result = service.getResult(API_KEY);
            result.enqueue(new Callback<ArtisTopAlbum>() {
                @Override
                public void onResponse(Call<ArtisTopAlbum> call, Response<ArtisTopAlbum> response) {

                    List<Album> results = response.body().getTopalbums().getAlbum();
                    int responsecode = response.code();
                    Log.i("response", String.valueOf(responsecode));
                    Log.i("json code ", new GsonBuilder().setPrettyPrinting().create().toJson(results));

                    adapter = new Adapter(results,R.layout.list_item, getApplicationContext());

                    recyclerView.setAdapter(adapter);


                }

                @Override
                public void onFailure(Call<ArtisTopAlbum> call, Throwable t) {

                    Log.e("failure", String.valueOf(t));
                }
            });

        }
        return true;


    }



}
