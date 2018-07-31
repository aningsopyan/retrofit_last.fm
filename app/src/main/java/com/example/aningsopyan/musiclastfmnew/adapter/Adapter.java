package com.example.aningsopyan.musiclastfmnew.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aningsopyan.musiclastfmnew.R;
import com.example.aningsopyan.musiclastfmnew.TrackDetail;
import com.example.aningsopyan.musiclastfmnew.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

    private List<Album> albums;
    private String  url_image_album;
    private Context context;
    private List<Album> mArrayList;
    private static LinearLayout linearLayout;


    public static class Holder extends RecyclerView.ViewHolder{


        TextView name,playcount,artist;
        ImageView image_album;

        public Holder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_item);
            name = itemView.findViewById(R.id.name);
            artist = itemView.findViewById(R.id.artist);
            playcount = itemView.findViewById(R.id.playcount);
            image_album = itemView.findViewById(R.id.image_album);

        }
    }


    public Adapter(List<Album> albums,int rowLayout, Context context){
        this.mArrayList = new ArrayList<>();
        this.mArrayList.addAll(albums);
        this.albums = albums;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        String name = String.valueOf(albums.get(position).getName());
        String playcount = albums.get(position).getPlaycount().toString();
        String artist = String.valueOf(albums.get(position).getArtist().getName());
        url_image_album = String.valueOf(albums.get(position).getImage().get(1).getText());

        holder.name.setText("Album Name : " + name);
        holder.artist.setText("Artist : " +artist);
        holder.playcount.setText("Playcount : "+playcount);

        try {
            Picasso.with(context)
                    .load(url_image_album)
                    .resize(130,130)
                    .into(holder.image_album);
        }
        catch (Exception e){
            e.getMessage();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String album_name = String.valueOf(albums.get(position).getName());
                Intent intent = new Intent(context,TrackDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("album_name",String.valueOf(album_name));
                intent.putExtra("image_album",String.valueOf(albums.get(position).getImage().get(2).getText()));
                context.startActivity(intent);

            }
        });

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.rotate);
        linearLayout.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        albums.clear();

        if (charText.length() == 0){
            albums.addAll(mArrayList);
        }
        else {
            for (Album wp : mArrayList){
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    albums.add(wp);
                }
            }
        }

        notifyDataSetChanged();
    }
}
