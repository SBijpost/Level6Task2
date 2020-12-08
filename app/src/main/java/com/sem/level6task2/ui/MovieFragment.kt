package com.sem.level6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sem.level6task2.R
import com.sem.level6task2.adapter.MovieAdapter
import com.sem.level6task2.model.MovieItem
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieFragment : Fragment() {
    private val movies = arrayListOf<MovieItem>()
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        rvMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieAdapter

        initViews()
        observeMovies()
    }

    private fun initViews() {
        rvMovies.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieAdapter
    }

    private fun observeMovies() {
        viewModel.movieItems.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun onMovieClick(movieItem: MovieItem) {
        Snackbar.make(rvMovies, "This movie is: ${movieItem.year}", Snackbar.LENGTH_LONG).show()
    }
}