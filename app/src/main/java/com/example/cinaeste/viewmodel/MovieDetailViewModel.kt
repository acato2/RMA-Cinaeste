package com.example.cinaeste.viewmodel

import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository

class MovieDetailViewModel {
    fun getMovieByTitle(name : String): Movie {
        val movies : ArrayList<Movie> = arrayListOf() //inicijaliziramo
        movies.addAll(MovieRepository.getFavoriteMovies())
        movies.addAll(MovieRepository.getRecentMovies())
        val movie = movies.find{movie -> name.equals(movie.title)}
        return movie ?:Movie(0,"Test","Test","Test","Test","Test")

    }
}