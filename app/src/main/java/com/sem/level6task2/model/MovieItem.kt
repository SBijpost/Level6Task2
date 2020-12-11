package com.sem.level6task2.model

import com.beust.klaxon.Json

data class MovieItem(

    val id: Int,
    val title: String,
    val release_date: String,
    val vote_average: Double,
    val overview: String,
    val poster_path: String,

) {
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500/$poster_path"
}
