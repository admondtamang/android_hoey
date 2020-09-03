package com.Softwarica.hoey.Fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


import com.Softwarica.hoey.Adapter.SongAdapter;
import com.Softwarica.hoey.Api.SongApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.R;
import com.google.android.exoplayer2.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {

    EditText searchView;
    RecyclerView recyclerSearch;
    List<Song> songList;
    ArrayAdapter<String> adapter;
    SongAdapter songAdapter;
    private static final String TAG = "Search";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerSearch = v.findViewById(R.id.recycleSongsSearch);
        searchView = v.findViewById(R.id.searchView);
        loadJsonSongs();

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        return v;
    }

    private void filter(String text) {

        ArrayList<Song> filteredList=new ArrayList<>();
        for(Song item: songList){
            if( item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        songAdapter.filterList(filteredList);
    }

      private void loadJsonSongs() {

        try {
            SongApi storyApi = Url.getInstance().create(SongApi.class);
            Call<List<Song>> storyCall = storyApi.getAllSongs();
            storyCall.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {

                    songList = response.body();
                    // To view story
                     songAdapter = new SongAdapter(getActivity(),songList);
                    recyclerSearch.setAdapter(songAdapter);
                    recyclerSearch.setLayoutManager(new LinearLayoutManager(getContext()));
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
