package com.example.cinaeste.viewmodel

import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository

class MovieListViewModel {
    fun getFavoriteMovie():List<Movie>{
        return MovieRepository.getFavoriteMovies()
    }
    fun getRecentMovie():List<Movie>{
        return MovieRepository.getRecentMovies()
    }
}