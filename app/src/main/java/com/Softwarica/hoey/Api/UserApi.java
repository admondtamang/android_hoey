package com.Softwarica.hoey.Api;

import com.Softwarica.hoey.Model.Favourite;
import com.Softwarica.hoey.Model.Song;
import com.Softwarica.hoey.Model.User;
import com.Softwarica.hoey.serverresponse.ImageResponse;
import com.Softwarica.hoey.serverresponse.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserApi {
    @FormUrlEncoded
    @POST("users/login")
    Call<UserResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @POST("users/signup")
    Call<UserResponse> registerUser(@Body User user);

    @GET("users/me")
    Call<User> userDetail(@Header("Authorization") String token);

    @PUT("users/me")
    Call<User> updateUser(@Header("Authorization") String token, @Body User user);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    // Get favourite songs
    @GET("/users/favourite")
    Call<User> getFavouriteSongs(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("users/favourite")
    Call<Void> addToFavourite(@Header("Authorization") String token, @Field("songId") String songId);

    @DELETE("users/favourite")
    Call<Void> deleteFavourtie(@Header("Authorization") String token, @Path("id") String id);



}
