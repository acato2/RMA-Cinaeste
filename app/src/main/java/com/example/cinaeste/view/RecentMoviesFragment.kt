package com.example.cinaeste.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.MovieDetailActivity
import com.example.cinaeste.R
import com.example.cinaeste.data.Movie
import com.example.cinaeste.viewmodel.MovieListViewModel

class RecentMoviesFragment : Fragment() {
    private lateinit var recentMovies : RecyclerView
    private lateinit var recentMoviesAdapter : MovieListAdapter
    private var movieListViewModel=MovieListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.recents_fragment, container, false)
        recentMovies = view.findViewById(R.id.recentMovies)
        recentMovies.layoutManager=GridLayoutManager(activity,2)
        recentMoviesAdapter = MovieListAdapter(arrayListOf()){
            movie -> showMovieDetails(movie)
        }
        recentMovies.adapter=recentMoviesAdapter
        recentMoviesAdapter.updateMovies(movieListViewModel.getRecentMovie())
        return view

    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_title",movie.title)
        }
        startActivity(intent)

    }

    companion object {
        fun newInstance(): RecentMoviesFragment = RecentMoviesFragment()
    }
}