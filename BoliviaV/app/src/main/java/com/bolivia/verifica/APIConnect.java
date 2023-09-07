package com.bolivia.verifica;

import com.bolivia.verifica.Interfaces.JsonDecoder;
import com.bolivia.verifica.Moduls.UnsafeOkHttpClient;
import com.bolivia.verifica.WpModel.Posts;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConnect {


    private static final String baseURl = "https://boliviaverifica.bo/wp-json/wp/v2/";
    private static Retrofit retrofit = null;

    List<Posts> posts;

    public APIConnect(){


    }
    public static JsonDecoder getRetrofit(){
        if(retrofit == null ){
            retrofit =new Retrofit.Builder().baseUrl(baseURl).client(UnsafeOkHttpClient.getUnsafeOkHttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(JsonDecoder.class);

    }
}
