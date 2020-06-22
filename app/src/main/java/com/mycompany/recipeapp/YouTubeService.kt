package com.mycompany.recipeapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("search?part=snippet")
    fun youtubeSearch(
        @Query("maxResults") maxResults: Int,
        @Query("order") order: String,
        @Query("q") query: String,
        @Query("key") appKey: String
    ) : Call<Kind>

    @GET("videos?part=snippet")
    fun videoDetails(
        @Query("id") id: String,
        @Query("key") appKey: String
    ) : Call<Kind>
}