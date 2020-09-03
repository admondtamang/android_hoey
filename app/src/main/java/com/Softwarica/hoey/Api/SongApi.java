package com.Softwarica.hoey.Api;

import com.Softwarica.hoey.Model.Comment;
import com.Softwarica.hoey.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SongApi {
    @GET("/songs")
    Call<List<Song>> getAllSongs();

    @GET("/songs/top")
    Call<List<Song>> getTopSongs();

    // Get songs from album
    @GET("/songs/album/{sId}")
    Call<List<Song>> getAlbumSongs(@Path("sId") String sId);

    // Get songs from artist
    @GET("/songs/{sId}")
    Call<List<Song>> getArtistsSongs(@Path("sId") String sId);


    // TO increse song count
    @PUT("/songs/counter/{sId}")
    Call<Void> incrementPlayCounter(@Path("sId") String sId);


    // To comment on song
    @GET("/songs/{sId}/comments")
    Call<List<Comment>> showAllComments(@Path("sId") String sId);

    @POST("/songs/{sId}/comments")
    Call<Void> postComment(@Header("Authorization") String token, @Path("sId") String sId,@Body Comment comment);

    @GET("/songs/{sId}/comments/{cId}")
    Call<Void> showOneComment(@Header("Authorization") String token, @Path("sId")String sid,@Path("cId") String cId);

    @PUT("/songs/{sId}/comments/{cId}")
    Call<Void> updateComment(@Header("Authorization") String token, @Path("sId") String sid, @Path("cId") String cId, @Body Comment comment);
//    Call<Void> updateComment(@Header("Authorization")String token, @Body Comment comment);

    @DELETE("/songs/{sId}/comments/{cId}")
    Call<Void> deleteComment(@Header("Authorization") String token, @Path("sId")String sid,@Path("cId") String cId);

}
