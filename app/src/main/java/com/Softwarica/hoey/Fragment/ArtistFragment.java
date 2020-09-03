package com.Softwarica.hoey.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Softwarica.hoey.Adapter.ArtistAdapter;
import com.Softwarica.hoey.Api.ArtistApi;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Model.Artist;
import com.Softwarica.hoey.R;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {
    private static final String TAG = "Home";
    RecyclerView artistRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist, container, false);
        artistRecycler = v.findViewById(R.id.recycleArtist);
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

                    ArtistAdapter artistAdapter = new ArtistAdapter(artists, getActivity());
                    artistRecycler.setAdapter(artistAdapter);
                    artistRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

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


}
