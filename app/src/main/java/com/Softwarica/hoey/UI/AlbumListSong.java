package com.Softwarica.hoey.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Softwarica.hoey.Adapter.SongAdapter;
import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.R;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.util.Log;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumListSong extends AppCompatActivity {

    RecyclerView recycleSongAlbum;
    private static final String TAG = "AlbumListSong";
    private ImageView imgAlbum;
    private TextView tvAlbumName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list_song);

        recycleSongAlbum=findViewById(R.id.recycleSongsAlbum);
        imgAlbum=findViewById(R.id.imgAlbum);
        String img=Url.uploads+getIntent().getStringExtra("albumCover");
        Glide.with(getApplicationContext()).load(Url.uploads+getIntent().getStringExtra("albumCover")).into(imgAlbum);
        tvAlbumName=findViewById(R.id.tvAlbumName);
        tvAlbumName.setText(getIntent().getStringExtra("albumName"));
        loadJsonSongs();
    }


    private void loadJsonSongs() {

        try {
            SongApi songApi = Url.getInstance().create(SongApi.class);
            Call<List<Song>> storyCall = songApi.getAlbumSongs(getIntent().getStringExtra("albumId"));
            storyCall.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {

                    List<Song> songList = response.body();
                    // To view story
                    SongAdapter songAdapter = new SongAdapter(getApplication(),songList);
                    recycleSongAlbum.setAdapter(songAdapter);
                    recycleSongAlbum.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    Log.e(TAG, "onFailure: Passed" + songList);
                }

                @Override
                public void onFailure(Call<List<Song>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "onCreateView: :" + e.getLocalizedMessage(), e);
        }

    }

}
