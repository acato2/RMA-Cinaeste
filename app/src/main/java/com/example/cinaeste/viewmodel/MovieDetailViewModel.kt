package com.example.cinaeste.viewmodel

import android.util.Log
import com.example.cinaeste.data.ActorMovieRepository
import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository
import com.example.cinaeste.data.SimilarMoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.cinaeste.data.Result
import kotlin.reflect.KFunction0

class MovieDetailViewModel(private val movieRetrieved: ((movies: Movie) -> Unit)?,
                           private val actorsRetrieved:  ((actors: MutableList<String>) -> Unit)?,
                           private val similarMoviesRetrieved: ((similar: MutableList<String>) -> Unit)?
) {

    val scope = CoroutineScope(Job() +Dispatchers.Main)

    fun getMovieByTitle(name : String): Movie {
        val movies : ArrayList<Movie> = arrayListOf() //inicijaliziramo
        movies.addAll(MovieRepository.getFavoriteMovies())
        movies.addAll(MovieRepository.getRecentMovies())
        val movie = movies.find{movie -> name.equals(movie.title)}
        return movie ?:Movie(0,"Test","Test","Test","Test","Test","Test","Test")

    }

    fun getActorsByTitle(name: String): List<String> {
        return ActorMovieRepository.getActorMovies()?.get(name)?: emptyList()

    }

    fun getSimilarMoviesByTitle(name: String): List<String> {
        return SimilarMoviesRepository.getSimilarMovies()?.get(name)?: emptyList()


    }

    fun getMovieDetails(query : Long){
        scope.launch {
            val result = MovieRepository.getMovieDetails(query)
            when(result){
                is Result.Success<Movie>->movieRetrieved?.invoke(result.data)
                else -> Log.v("meh","meh")
            }
        }
    }
    fun getActors(query : Long){
        scope.launch {
            val result = ActorMovieRepository.getActors(query)
            when(result){
                is Result.Success<MutableList<String>>->actorsRetrieved?.invoke(result.data)
                else -> Log.v("meh","meh")
            }
        }
    }
    fun getSimilarMovies(query : Long){
        scope.launch {
            val result = SimilarMoviesRepository.getSimilarMoviesAPI(query)
            when(result){
                is Result.Success<MutableList<String>>->similarMoviesRetrieved?.invoke(result.data)
                else -> Log.v("meh","meh")
            }
        }
    }
}