package com.Softwarica.hoey.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Artist;
import com.Softwarica.hoey.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static com.Softwarica.hoey.Utils.StrictModeClass.StrictMode;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private List<Artist> artistList;
    private Context con;

    public ArtistAdapter(List<Artist> artistList, Context con) {
        this.artistList = artistList;
        this.con = con;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        final Artist artist = artistList.get(position);
        holder.tvArtistName.setText(artist.getArtistName());
        String imgPath = Url.uploads + artist.getArtistImage();
        StrictMode();

        try {
            URL url = new URL(imgPath);
            holder.imgArtistImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        TextView tvArtistName;
        ImageView imgArtistImage;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArtistName = itemView.findViewById(R.id.imgArtistName);
            imgArtistImage = itemView.findViewById(R.id.imgArtistImage);
        }
    }
}
