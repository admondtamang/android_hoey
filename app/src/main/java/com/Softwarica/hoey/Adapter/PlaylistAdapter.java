package com.Softwarica.hoey.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Model.Playlist;
import com.Softwarica.hoey.R;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>  {

      private List<Playlist> playlistList;
    private Context con;

    public PlaylistAdapter(List<Playlist> playlistList, Context con) {
        this.playlistList = playlistList;
        this.con = con;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        final Playlist playlist=playlistList.get(position);
        holder.tvPlaylistName.setText(playlist.getPlaylistName());
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlaylistName;
        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaylistName=itemView.findViewById(R.id.tvPlaylistName);

        }
    }
}
