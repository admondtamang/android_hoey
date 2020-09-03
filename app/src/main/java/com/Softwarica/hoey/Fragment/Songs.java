package com.Softwarica.hoey.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softwarica.hoey.Adapter.SongAdapter;
import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
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
public class Songs extends Fragment {
    private static final String TAG = "Songs";
        RecyclerView recyclerViewSong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_songs, container, false);
        recyclerViewSong=v.findViewById(R.id.recycleSongsFrag);
        loadJsonSongs();
        return v;
    }


    private void loadJsonSongs() {

        try {
            SongApi storyApi = Url.getInstance().create(SongApi.class);
            Call<List<Song>> storyCall = storyApi.getAllSongs();
            storyCall.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {

                    List<Song> songList = response.body();
                    // To view story
                    SongAdapter songAdapter = new SongAdapter(getActivity(),songList);
                    recyclerViewSong.setAdapter(songAdapter);
                    recyclerViewSong.setLayoutManager(new LinearLayoutManager(getContext()));
//                    swipeRefreshLayout.setRefreshing(false);
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
