package com.Softwarica.hoey.Api;

import com.Softwarica.hoey.Model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumApi {
    @GET("/admin/albums")
    Call<List<Album>> getAllAlbums();

    @GET("/admin/albums/{sId}")
    Call<Album> getSong(@Path("sId") String sId);
}
