package com.Softwarica.hoey.Api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
//    public static final String BASE_URL = "http://192.168.0.102" +":3001/";
    public static final String BASE_URL = "http://192.168.1.74" +":3001/";
//    public static final String BASE_URL = "http://192.168.43.35" +":3001/";
     public static String token = "Bearer ";

     public static String uploads=BASE_URL+"uploads/";
//    public static String music=BASE_URL+"uploads/songs/";
//    public static String albumsImg=BASE_URL+"uploads/albums/";
    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}