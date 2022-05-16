package com.example.cinaeste.data

import com.example.cinaeste.BuildConfig
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

sealed class Result<out R>{
    data class Success<out T>(val data : T):Result<T>()
    data class Error(val exception : Exception):Result<Nothing>()
}

object MovieRepository {
    private const val tmdb_api_key : String = BuildConfig.TMDB_API_KEY
    fun getFavoriteMovies(): List<Movie>{
        return favoriteMovies()
    }
    fun getRecentMovies(): List<Movie>{
        return recentMovies()
    }

    suspend fun searchRequest(
        query : String
    ):Result<List<Movie>>{
        return withContext(Dispatchers.IO){
            try{
                val movies = arrayListOf<Movie>()
                val url1 = "https://api.themoviedb.org/3/search/movie?api_key=$tmdb_api_key&query=$query"
                val url = URL(url1)
                (url.openConnection() as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use {it.readText()}
                    val jo = JSONObject(result)
                    val results = jo.getJSONArray("results")
                    for(i in 0 until results.length()){
                        val movie = results.getJSONObject(i)
                        val title = movie.getString("title")
                        val id = movie.getInt("id")
                        val posterPath = movie.getString("poster_path")
                        val overview = movie.getString("overview")
                        val releaseDate = movie.getString("release_date")
                        val backdropPath = movie.getString("backdrop_path")
                        movies.add(Movie(id.toLong(),title,overview,releaseDate,null,null,posterPath,backdropPath))
                        if(i==5)break
                    }

                }
                return@withContext Result.Success(movies)
            }
            catch(e : MalformedURLException){
                return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
            }
            catch(e : IOException){
                return@withContext Result.Error(Exception("Cannot read stream"))
            }
            catch(e : JSONException){
                return@withContext Result.Error(Exception("Cannot parse JSON"))
            }
        }
    }
    suspend fun getMovieDetails(
        id : Long
    ):Result<Movie>{
        return withContext(Dispatchers.IO){
            try{
                val url1 = "https://api.themoviedb.org/3/movie/$id?api_key=$tmdb_api_key"
                val url = URL(url1)
                var movie = Movie(0, "Test", "Test", "Test", "Test", "Test", "Test","Test")
                (url.openConnection() as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use {
                        it.readText()
                    }
                    val jo = JSONObject(result)
                    movie.title = jo.getString("title")
                    movie.id = jo.getLong("id")
                    movie.posterPath = jo.getString("poster_path")
                    movie.overview = jo.getString("overview")
                    movie.releaseDate = jo.getString("release_date")
                    movie.homepage = jo.getString("homepage")
                    movie.genre = jo.getJSONArray("genres").getJSONObject(0).getString("name")
                    movie.backdropPath = jo.getString("backdrop_path")


                }
                return@withContext Result.Success(movie)
            }
            catch(e : MalformedURLException){
                return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
            }
            catch(e : IOException){
                return@withContext Result.Error(Exception("Cannot read stream"))
            }
            catch(e : JSONException){
                return@withContext Result.Error(Exception("Cannot parse JSON"))
            }
        }

    }



}