package com.example.cinaeste.viewmodel

import android.content.Context
import com.example.cinaeste.data.GetMoviesResponse
import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.cinaeste.data.Result

class MovieListViewModel(private val searchDone : ((movies:List<Movie>) -> Unit)?,
                            private val onError : (()->Unit)?) {
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    fun getFavorites(context: Context, onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getFavoriteMovies(context)
            when (result) {
                is List<Movie> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun getRecentMovie():List<Movie>{
        return MovieRepository.getRecentMovies()
    }
    fun search(query: String){

        // Create a new coroutine on the UI thread
        scope.launch{

            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.searchRequest(query)

            // Display result of the network request to the user
            when (result) {
                is Result.Success<List<Movie>> -> searchDone?.invoke(result.data)
                else-> onError?.invoke()
            }
        }
    }
    fun getUpcoming( onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getUpcomingMovies()
            when (result) {
                is GetMoviesResponse -> onSuccess?.invoke(result.movies)
                else-> onError?.invoke()
            }
        }
    }

}