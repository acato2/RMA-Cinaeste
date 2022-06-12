package com.example.cinaeste.data

import com.example.cinaeste.BuildConfig
import com.example.cinaeste.view.ApiAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ActorMovieRepository {

    private const val tmdb_api_key = BuildConfig.TMDB_API_KEY
    fun getActorMovies():Map<String,List<String>>{
        return movieActors()
    }

    suspend fun getCast( id: Long
    ) : GetCastResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getCast(id)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }

}