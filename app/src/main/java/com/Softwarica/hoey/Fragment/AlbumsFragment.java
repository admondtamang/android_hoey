package com.Softwarica.hoey.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Softwarica.hoey.Adapter.AlbumAdapter;
import com.Softwarica.hoey.Api.AlbumApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Album;
import com.Softwarica.hoey.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {

    private RecyclerView albumRecycler;

    private static final String TAG = "AlbumsFragment";
    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_albums, container, false);
        albumRecycler = v.findViewById(R.id.recycleAlbumLibrary);
        loadJsonAlbum();
        return v;

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
                    AlbumAdapter songAdapter = new AlbumAdapter(albumList, getActivity());
                    albumRecycler.setAdapter(songAdapter);
                    albumRecycler.setLayoutManager(layoutManager);
                }

                @Override
                public void onFailure(Call<List<Album>> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "onCreateView: :" + e.getLocalizedMessage(), e);
        }

    }

}
