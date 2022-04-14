package com.example.cinaeste.data

object SimilarMoviesRepository {
    fun getSimilarMovies(): Map<String,List<String>> {
        return movieSimilar()
    }
}