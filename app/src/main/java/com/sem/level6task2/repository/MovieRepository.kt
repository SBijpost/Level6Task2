package com.sem.level6task2.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sem.level6task2.`interface`.EndPoints
import com.sem.level6task2.model.MovieItem
import com.sem.level6task2.model.MovieList
import com.sem.level6task2.service.ServiceBuilder
import com.sem.level6task2.ui.MovieFragment
import com.sem.level6task2.ui.MovieViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private var movies = listOf<MovieItem>()

    fun getMovieItems(year: String, callback: ApiCallback) {

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getMovies("fbfa2564113ded8c8f1b99d349f2a035", year, "en-US")

        call.enqueue(object : Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful){
                    movies = response.body()!!.results
                    callback.onSuccess(movies)
                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

}

interface ApiCallback {
    fun onSuccess(result: List<MovieItem>)
}