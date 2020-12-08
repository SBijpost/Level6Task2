package com.sem.level6task2.model

data class MovieItem(
    var year: Int
) {
    fun getMovieUrl() = "https://api.themoviedb.org/3/discover/movie?api_key=fbfa2564113ded8c8f1b99d349f2a035&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_year=$year"
}
