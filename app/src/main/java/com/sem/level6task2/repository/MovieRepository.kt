package com.sem.level6task2.repository

import com.sem.level6task2.model.MovieItem

class MovieRepository {
    fun getMovieItems(): List<MovieItem> {
        return arrayListOf(
            MovieItem("000000", "Black"),
            MovieItem("FF0000", "Red"),
            MovieItem("0000FF", "Blue"),
            MovieItem("FFFF00", "Yellow"),
            MovieItem("008000", "Green")
        )
    }
}