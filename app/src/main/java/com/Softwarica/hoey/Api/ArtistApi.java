package com.Softwarica.hoey.Api;

import com.Softwarica.hoey.Model.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArtistApi {

    @GET("/admin/artists")
    Call<List<Artist>> getAllArtists();

    @GET("/admin/artists/top")
    Call<List<Artist>> getTopArtist();

}
