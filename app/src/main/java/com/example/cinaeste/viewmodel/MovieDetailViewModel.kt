package com.example.cinaeste.viewmodel

import android.content.Context
import com.example.cinaeste.data.Cast
import com.example.cinaeste.data.GetCastResponse
import com.example.cinaeste.data.GetSimilarResponse
import com.example.cinaeste.data.ActorMovieRepository
import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailViewModel(
) {

    val scope = CoroutineScope(Job() +Dispatchers.Main)


    fun getMovieByTitle(name:String):Movie{
        var movies: ArrayList<Movie> = arrayListOf()
        val movie= movies.find { movie -> name.equals(movie.title) }
        return movie?:Movie(0,"Test","Test","Test","Test","Test","Test")
    }

    fun getSimilarMoviesById(query: Long, onSuccess: (movies: List<Movie>) -> Unit,
                             onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getSimilarMovies(query)
            when (result) {
                is GetSimilarResponse -> onSuccess?.invoke(result.movies)
                else-> onError?.invoke()
            }
        }
    }
    fun getSimilarMoviesByIdDB(context: Context,id: Long, onSuccess: (movies: List<Movie>) -> Unit,
                               onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getSimilarMoviesDB(context,id)
            when (result) {
                is List<*> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }
    fun getActorsById(query: Long, onSuccess: (actors: List<Cast>) -> Unit,
                      onError: () -> Unit){
        scope.launch{
            val result = ActorMovieRepository.getCast(query)
            when (result) {
                is GetCastResponse -> onSuccess?.invoke(result.cast)
                else-> onError?.invoke()
            }
        }
    }
    fun getActorsByIdDB(context: Context, id: Long, onSuccess: (actors: List<Cast>) -> Unit,
                        onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getCastDB(context,id)
            when (result) {
                is List<*> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }
    fun writeDB(context: Context, movie:Movie, onSuccess: (movies: String) -> Unit,
                onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.writeFavorite(context,movie)
            when (result) {
                is String -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun getMovieFromDB(context: Context, id:Long, onSuccess: (movies: Movie) -> Unit,
                       onError: () -> Unit){


        scope.launch{
            val result = MovieRepository.getMovieDB(context,id)
            when (result) {
                is Movie -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun getMovie(query: Long, onSuccess: (movies: Movie) -> Unit,
                 onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getMovie(query)
            when (result) {
                is Movie -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }
}




