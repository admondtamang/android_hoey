package com.Softwarica.hoey.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
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

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static com.Softwarica.hoey.Utils.StrictModeClass.StrictMode;

public class HomeSongAdapter extends RecyclerView.Adapter<HomeSongAdapter.AlbumViewHolder>{
    private static final String TAG = "AlbumAdapter";
    private List<Album> albumList;
    private Context con;

    public HomeSongAdapter(List<Album> albumList, Context con) {
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
        Log.e(TAG, "onBindViewHolder: "+album.getAlbumCover()+" = "+album.getAlbumTitle() );
        StrictMode();

        try {
            URL url = new URL(imgPath);
            holder.imgAlbum.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
        }
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
