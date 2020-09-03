package com.Softwarica.hoey.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Album;
import com.Softwarica.hoey.R;
import com.Softwarica.hoey.UI.AlbumListSong;
import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{
    private static final String TAG = "AlbumAdapter";
    private List<Album> albumList;
    private Context con;

    public AlbumAdapter(List<Album> albumList, Context con) {
        this.albumList = albumList;
        this.con = con;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
     final Album album = albumList.get(position);
        holder.tvAlbumname.setText(album.getAlbumTitle());
        String imgPath = Url.uploads + album.getAlbumCover();
        Glide.with(con).load(imgPath).into(holder.imgAlbum);
        holder.imgAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(con, AlbumListSong.class);
                    intent.putExtra("albumId", album.get_id());
                    intent.putExtra("albumName", album.getAlbumTitle());
                    intent.putExtra("albumCover", album.getAlbumCover());
                Log.e(TAG, "onClick: "+album.getAlbumTitle()+album.getAlbumCover());
                    con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
      TextView tvAlbumname;
        ImageView imgAlbum;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlbumname=itemView.findViewById(R.id.tvSongName);
            imgAlbum=itemView.findViewById(R.id.imgSongImage);
        }
    }
}
