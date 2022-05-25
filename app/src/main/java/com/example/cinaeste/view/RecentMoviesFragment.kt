package com.example.cinaeste.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var movieListViewModel=MovieListViewModel(null,null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.recents_fragment, container, false)
        recentMovies = view.findViewById(R.id.recentMovies)
        recentMovies.layoutManager=GridLayoutManager(activity,2)
        recentMoviesAdapter = MovieListAdapter(arrayListOf()) { movie,view1,view2 -> showMovieDetails(movie,view1,view2) }
        recentMovies.adapter=recentMoviesAdapter
        recentMoviesAdapter.updateMovies(movieListViewModel.getRecentMovie())
        movieListViewModel.getUpcoming(
            onSuccess = ::onSuccess,
            onError = ::onError
        )

        return view

    }

    private fun showMovieDetails(movie: Movie, view1: View,view2:View) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_title", movie.title)
        }
        val options = ActivityOptions
            .makeSceneTransitionAnimation(activity,  Pair.create(view1, "poster"),
                Pair.create(view2, "title"))
        startActivity(intent, options.toBundle())
    }

    companion object {
        fun newInstance(): RecentMoviesFragment = RecentMoviesFragment()
    }
    fun onSuccess(movies:List<Movie>){
        val toast = Toast.makeText(context, "Upcoming movies found", Toast.LENGTH_SHORT)
        toast.show()
        recentMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
}