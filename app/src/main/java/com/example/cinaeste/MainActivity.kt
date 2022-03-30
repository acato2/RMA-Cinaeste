package com.example.cinaeste

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.data.Movie
import com.example.cinaeste.view.MovieListAdapter
import com.example.cinaeste.viewmodel.MovieListViewModel

class MainActivity : AppCompatActivity(){
    private lateinit var favoriteMovies : RecyclerView
    private lateinit var favoriteMoviesAdapter : MovieListAdapter
    private var movieListViewModel = MovieListViewModel()

    private lateinit var recentMovies : RecyclerView
    private lateinit var recentMoviesAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favoriteMovies=findViewById(R.id.favoriteMovies)
        favoriteMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteMoviesAdapter= MovieListAdapter(arrayListOf()){movie -> showMovieDetails(movie)}
        favoriteMovies.adapter=favoriteMoviesAdapter
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getFavoriteMovie())

        recentMovies=findViewById(R.id.recentMovies)
        recentMovies.layoutManager=LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recentMoviesAdapter= MovieListAdapter(arrayListOf()){movie -> showMovieDetails(movie)}
        recentMovies.adapter=recentMoviesAdapter
        recentMoviesAdapter.updateMovies(movieListViewModel.getRecentMovie())



    }
    private fun showMovieDetails(movie:Movie){
        val intent = Intent(this,MovieDetailActivity::class.java).apply {
            putExtra("movie_title",movie.title)
        }
        startActivity(intent)
    }

}