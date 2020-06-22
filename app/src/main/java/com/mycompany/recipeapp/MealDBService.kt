package com.mycompany.recipeapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBService {
    @GET("search.php")
    fun search(
        @Query("s") recipeSearch: String
    ) : Call<MealDBData>

    @GET("categories.php")
    fun getCat(

    ) : Call<MealDBData>

    @GET("filter.php")
    fun catFilter(
        @Query("c") categoryFilter: String
    ) : Call<MealDBData>

    @GET("lookup.php")
    fun idLookup(
        @Query("i") mealId: Int
    ) : Call<MealDBData>
}