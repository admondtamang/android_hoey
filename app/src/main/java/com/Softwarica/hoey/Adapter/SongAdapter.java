package com.Softwarica.hoey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Model.Album;
import com.Softwarica.hoey.Model.Artist;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.NowPlaying;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> songList;
    private Context con;

    public SongAdapter(Context con, List<Song> songList) {
        this.songList = songList;
        this.con = con;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, final int position) {

        final Song song = songList.get(position);
        holder.tvSongName.setText(song.getTitle());
        final Album album = song.getAlbum();
        final Artist artist = album.getArtist();
        holder.tvSongArtist.setText(artist.getArtistName());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent
                Intent intent = new Intent(con, NowPlaying.class);
                Bundle bundle=new Bundle();
                bundle.putString("songId",song.get_id());
                bundle.putString("link",song.getLink());
                bundle.putString("songTitle", song.getTitle());
                bundle.putString("played",song.getPlayed());
                bundle.putString("artistName", artist.getArtistName());
                bundle.putString("albumCover",album.getAlbumCover());
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                con.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void filterList(ArrayList<Song> filteredList) {
        songList = filteredList;
        notifyDataSetChanged();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        TextView tvSongName, tvSongArtist;
        ImageView imgAlbum;
        ConstraintLayout constraintLayout;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongName = itemView.findViewById(R.id.tvSongName);
            tvSongArtist = itemView.findViewById(R.id.tvSongArtist);
            imgAlbum = itemView.findViewById(R.id.imgAlbumCover);
            constraintLayout = itemView.findViewById(R.id.constraintSongItem);

        }
    }
}
