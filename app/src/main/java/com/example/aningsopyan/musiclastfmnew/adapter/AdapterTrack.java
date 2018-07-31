package com.example.aningsopyan.musiclastfmnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aningsopyan.musiclastfmnew.MainActivity;
import com.example.aningsopyan.musiclastfmnew.R;
import com.example.aningsopyan.musiclastfmnew.albumtrack.AlbumTrack;
import com.example.aningsopyan.musiclastfmnew.albumtrack.Track;
import com.example.aningsopyan.musiclastfmnew.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterTrack extends RecyclerView.Adapter<AdapterTrack.Holder>{

    private List<Track> tracks;
    private Context context;
    private static LinearLayout linearLayout;

    public static class Holder extends RecyclerView.ViewHolder{


        TextView name;
        ImageView imagetoolbar;

        public Holder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_item);
            name = itemView.findViewById(R.id.name);
            imagetoolbar = itemView.findViewById(R.id.toolbarImage);

        }
    }

    public AdapterTrack(List<Track> tracks, int rowLayout, Context context){

        this.tracks = tracks;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_track_album,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        holder.name.setText(String.valueOf(tracks.get(position).getName()));
        Log.i("namaalbum",String.valueOf(tracks.get(position).getName()));

    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }



}
