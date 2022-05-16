package com.example.cinaeste.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.R
import com.example.cinaeste.viewmodel.MovieDetailViewModel

class SimilarMoviesFragment(movieName : String,movieId : Long?) : Fragment() {
    private var movieName : String = movieName
    private var movieId:Long? = movieId
    private lateinit var movieRV : RecyclerView
    private var similarMoviesList = listOf<String>()
    private lateinit var similarMoviesRVSimpleAdapter : SimpleStringAdapter
    private var movieDetailViewModel = MovieDetailViewModel(null,null,this@SimilarMoviesFragment::similarRetrieved)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.similar_movies_fragment, container, false)
        movieRV = view.findViewById(R.id.listSimilar)
        similarMoviesList =movieName?.let { movieDetailViewModel.getSimilarMoviesByTitle(movieName)}
        movieRV.layoutManager=LinearLayoutManager(activity)
        similarMoviesRVSimpleAdapter = SimpleStringAdapter(similarMoviesList)
        movieRV.adapter=similarMoviesRVSimpleAdapter
        movieId?.let { movieDetailViewModel.getSimilarMovies(it) }

        return view
    }
    fun similarRetrieved(similar:MutableList<String>){
        similarMoviesList=similar
        similarMoviesRVSimpleAdapter.list = similar;
        similarMoviesRVSimpleAdapter.notifyDataSetChanged();
    }


}