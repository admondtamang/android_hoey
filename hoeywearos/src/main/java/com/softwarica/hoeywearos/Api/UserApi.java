package com.softwarica.hoeywearos.Api;

import com.softwarica.hoeywearos.Model.User;
import com.softwarica.hoeywearos.serverresponse.ImageResponse;
import com.softwarica.hoeywearos.serverresponse.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
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

}
