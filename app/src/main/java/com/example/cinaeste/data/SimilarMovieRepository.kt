package com.example.cinaeste.data

import com.example.cinaeste.BuildConfig
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

object SimilarMoviesRepository {
    private const val tmdb_api_key : String = BuildConfig.TMDB_API_KEY
    fun getSimilarMovies(): Map<String,List<String>> {
        return movieSimilar()
    }
    suspend fun getSimilarMoviesAPI(
        id : Long
    ):Result<MutableList<String>>{
        return withContext(Dispatchers.IO){
            try{
                val url1 = "https://api.themoviedb.org/3/movie/$id/similar?api_key=${SimilarMoviesRepository.tmdb_api_key}"
                val url = URL(url1)
                var similar = mutableListOf<String>()
                (url.openConnection() as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use {
                        it.readText()
                    }
                    val jo = JSONObject(result)
                    val items : JSONArray = jo.getJSONArray("results")
                    for(i in 0 until items.length()){
                        val slicni = items.getJSONObject(i)
                        val title = slicni.getString("title")
                        similar.add(title)
                        if(i==4)break
                    }

                }
                return@withContext Result.Success(similar)
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