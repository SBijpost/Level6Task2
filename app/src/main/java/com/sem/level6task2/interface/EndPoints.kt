package com.sem.level6task2.`interface`

import com.sem.level6task2.model.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {

    @GET("/3/discover/movie")
    fun getMovies(@Query("api_key") key: String, @Query("year") year: String, @Query("language") lang: String): Call<MovieList>

}