package com.example.cinaeste.viewmodel

import com.example.cinaeste.data.ActorMovieRepository
import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository
import com.example.cinaeste.data.SimilarMoviesRepository

class MovieDetailViewModel {
    fun getMovieByTitle(name : String): Movie {
        val movies : ArrayList<Movie> = arrayListOf() //inicijaliziramo
        movies.addAll(MovieRepository.getFavoriteMovies())
        movies.addAll(MovieRepository.getRecentMovies())
        val movie = movies.find{movie -> name.equals(movie.title)}
        return movie ?:Movie(0,"Test","Test","Test","Test","Test")

    }

    fun getActorsByTitle(name: String): List<String> {
        return ActorMovieRepository.getActorMovies()?.get(name)?: emptyList()

    }

    fun getSimilarMoviesByTitle(name: String): List<String> {
        return SimilarMoviesRepository.getSimilarMovies()?.get(name)?: emptyList()


    }
}