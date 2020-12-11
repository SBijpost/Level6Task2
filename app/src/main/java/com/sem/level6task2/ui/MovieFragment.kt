package com.sem.level6task2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    private lateinit var navController: NavController

    private var year: String = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        viewModelFactory = ViewModelFactory(year)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        rvMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieAdapter

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        initViews()

        submitButton.setOnClickListener {
            loadMovies()
        }
    }

    private fun initViews() {
        rvMovies.layoutManager =
                GridLayoutManager(context, 2)
        rvMovies.adapter = movieAdapter
    }

    private fun loadMovies() {
        viewModel.yearParam = tilMovieYear.text.toString()

        viewModel.movieItems.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun onMovieClick(movieItem: MovieItem) {
        val bundle = bundleOf("title" to movieItem.title, "overview" to movieItem.overview, "backdrop" to movieItem.getBackdropImage(), "date" to movieItem.release_date, "rating" to movieItem.vote_average, "poster" to movieItem.getImageUrl())
        navController.navigate(R.id.action_movieFragment_to_viewMovieDetailFragment, bundle)
    }
}