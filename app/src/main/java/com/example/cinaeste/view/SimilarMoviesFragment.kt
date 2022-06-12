package com.example.cinaeste.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.unsa.etf.cineaste.view.SimpleSimilarStringAdapter
import com.example.cinaeste.R
import com.example.cinaeste.data.Movie
import com.example.cinaeste.viewmodel.MovieDetailViewModel

class SimilarMoviesFragment(movieName : String,movieId : Long?,favourite:Boolean) : Fragment() {

    private var movieId:Long? = movieId
    private lateinit var movieRV : RecyclerView
    private var movieList= listOf<Movie>()
    private lateinit var similarMoviesRVSimpleAdapter : SimpleSimilarStringAdapter
    private var movieDetailViewModel = MovieDetailViewModel()
    private var favourite = favourite

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.similar_movies_fragment, container, false)
        movieRV = view.findViewById(R.id.listSimilar)
       // similarMoviesList =movieName?.let { movieDetailViewModel.getSimilarByTitle(movieName)}
        movieRV.layoutManager=LinearLayoutManager(activity)
        similarMoviesRVSimpleAdapter = SimpleSimilarStringAdapter(movieList)
        movieRV.adapter=similarMoviesRVSimpleAdapter
        if(favourite){
            movieId?.let { movieDetailViewModel.getSimilarMoviesByIdDB(requireContext(),it,onSuccess = ::onSuccess, onError = ::onError) }
        }else{
            movieId?.let { movieDetailViewModel.getSimilarMoviesById(it,onSuccess = ::onSuccess,
                onError = ::onError) }
        }

        return view
    }

    fun onSuccess(movies:List<Movie>){
        movieList=movies
        similarMoviesRVSimpleAdapter.list = movies;
        similarMoviesRVSimpleAdapter.notifyDataSetChanged();
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }

}