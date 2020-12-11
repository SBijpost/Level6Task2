package com.sem.level6task2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sem.level6task2.model.MovieItem
import com.sem.level6task2.repository.ApiCallback
import com.sem.level6task2.repository.MovieRepository

class MovieViewModel(year: String) : ViewModel(){
    private val movieRepository = MovieRepository()

    var yearParam = year

    //use encapsulation to expose as LiveData
    val movieItems: LiveData<List<MovieItem>>
        get() = _movieItems(yearParam)

    private fun _movieItems(year: String) = MutableLiveData<List<MovieItem>>().apply {
        movieRepository.getMovieItems(year, object : ApiCallback {
            override fun onSuccess(result: List<MovieItem>) {
                value = result
            }
        })
    }
}
