package com.sem.level6task2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sem.level6task2.model.MovieItem
import com.sem.level6task2.repository.MovieRepository

class MovieViewModel : ViewModel(){
    private val movieRepository = MovieRepository()

    //use encapsulation to expose as LiveData
    val movieItems: LiveData<List<MovieItem>>
        get() = _movieItems

    private val _movieItems = MutableLiveData<List<MovieItem>>().apply {
        value = movieRepository.getMovieItems()
    }
}
