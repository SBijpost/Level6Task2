package com.sem.level6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sem.level6task2.R
import com.sem.level6task2.model.MovieItem
import kotlinx.android.synthetic.main.fragment_view_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*

class ViewMovieDetailFragment: Fragment() {

    companion object {

        @JvmStatic
        fun newInstance(movieTitle: String, moviePoster: String, movieDate: String, movieRate: Double, movieOverview: String) = ViewMovieDetailFragment().apply {
            arguments = Bundle().apply {
                putString("MOVIE_TITLE", movieTitle)
                putString("MOVIE_POSTER", moviePoster)
                putString("MOVIE_DATE", movieDate)
                putDouble("MOVIE_RATE", movieRate)
                putString("MOVIE_OVERVIEW", movieOverview)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    fun initViews() {
        Glide.with(this).load(arguments?.getString("poster")).into(movie_poster_small)
        Glide.with(this).load(arguments?.getString("backdrop")).into(movie_poster_big)
        tvTitle.text = arguments?.getString("title")
        tvDate.text = arguments?.getString("date")
        tvRating.text = arguments?.getDouble("rating").toString()
        tvOverview.text = arguments?.getString("overview")
    }



}