package com.bolivia.verifica.Interfaces;

import com.bolivia.verifica.WpModel.Categories;
import com.bolivia.verifica.WpModel.Media;
import com.bolivia.verifica.WpModel.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonDecoder {
    @GET("posts")
    Call<List<Posts>> getPosts(@Query("page") int page);

    @GET("posts")
    Call<List<Posts>> PostByCategorie(@Query("categories") int categorie,@Query("page") int page);

    @GET("media/{id}")
    Call<Media> getImageUrl(@Path("id") int id);

    @GET("categories?exclude=620,459,8,267,3055,3230,9,3579,461,3")
    Call<List<Categories>> getCaategories(@Query("per_page") int page);

    @GET("posts?categories=620,298,459")
    Call<List<Posts>>getAperturas();

    @GET("posts?orderby=include")
    Call<List<Posts>> getMyPosts(@Query("include") String ids);

    @GET("posts")
    Call<List<Posts>> getsearch(@Query("search") String search);


}
