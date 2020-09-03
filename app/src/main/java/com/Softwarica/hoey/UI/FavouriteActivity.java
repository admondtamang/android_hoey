package com.Softwarica.hoey.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.Softwarica.hoey.Adapter.SongAdapter;
import com.Softwarica.hoey.Api.Url;
import com.Softwarica.hoey.Api.UserApi;
import com.Softwarica.hoey.Model.Favourite;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.Model.User;
import com.Softwarica.hoey.R;
import com.google.android.exoplayer2.util.Log;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity {


    RecyclerView recyclerFavourite;
    private static final String TAG = "FavouriteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        recyclerFavourite=findViewById(R.id.recycleFavourite);
        loadJsonSongs();
    }


     private void loadJsonSongs() {

        try {
            UserApi userApi = Url.getInstance().create(UserApi.class);
            Call<User> userCall = userApi.getFavouriteSongs(Url.token);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    User user = response.body();

                    List<Song> songList  = user.getSongs();
                    SongAdapter songAdapter = new SongAdapter(getApplication(),songList);
                    recyclerFavourite.setAdapter(songAdapter);
                    recyclerFavourite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    Log.e(TAG, "onFailure: Passed" + songList);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                }

            });


        } catch (Exception e) {
            Log.e(TAG, "onCreateView: :" + e.getLocalizedMessage(), e);
        }

    }
}
