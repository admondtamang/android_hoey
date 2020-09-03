package com.Softwarica.hoey.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softwarica.hoey.Adapter.AlbumAdapter;
import com.Softwarica.hoey.Adapter.ArtistAdapter;
import com.Softwarica.hoey.Adapter.SongAdapter;
import com.Softwarica.hoey.Api.AlbumApi;
import com.Softwarica.hoey.Api.ArtistApi;
import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Album;
import com.Softwarica.hoey.Model.Artist;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.R;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private static final String TAG = "Home";
    RecyclerView albumRecycler,songRecycler,artistRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        albumRecycler = v.findViewById(R.id.recycleAlbum);
        songRecycler=v.findViewById(R.id.recycleSong);
        artistRecycler=v.findViewById(R.id.recycleArtist);
        loadJsonSong();
        loadJsonAlbum();
        loadJsonArtists();
        return v;
    }

    private void loadJsonArtists() {
         try {
            ArtistApi songApi = Url.getInstance().create(ArtistApi.class);
            Call<List<Artist>> songCall = songApi.getTopArtist();
            songCall.enqueue(new Callback<List<Artist>>() {
                @Override
                public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {

                    List<Artist> artists = response.body();

                    ArtistAdapter artistAdapter = new ArtistAdapter(artists,getActivity());
                    artistRecycler.setAdapter(artistAdapter);
                    artistRecycler.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

                }

                @Override
                public void onFailure(Call<List<Artist>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "onCreateView: :" + e.getLocalizedMessage(), e);
        }
    }

    private void loadJsonSong() {
          try {
            SongApi songApi = Url.getInstance().create(SongApi.class);
            Call<List<Song>> songCall = songApi.getTopSongs();
            songCall.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {

                    List<Song> songList = response.body();

                    SongAdapter songAdapter = new SongAdapter(getActivity(),songList);
                    songRecycler.setAdapter(songAdapter);
                    songRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

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

    public void loadJsonAlbum() {

        try {
            AlbumApi albumApi = Url.getInstance().create(AlbumApi.class);
            Call<List<Album>> storyCall = albumApi.getAllAlbums();
            storyCall.enqueue(new Callback<List<Album>>() {
                @Override
                public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                    List<Album> albumList = response.body();
                    // To view story
                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    AlbumAdapter songAdapter = new AlbumAdapter(albumList,getActivity());
                    albumRecycler.setAdapter(songAdapter);
                    albumRecycler.setLayoutManager(layoutManager);
                    Log.e(TAG, "onFailure: Passed" + albumList);
                }

                @Override
                public void onFailure(Call<List<Album>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "onCreateView: :" + e.getLocalizedMessage(), e);
        }

    }

}
