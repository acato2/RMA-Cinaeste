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

class SimilarMoviesFragment(movieName : String) : Fragment() {
    private var movieName : String = movieName
    private var movieDetailViewModel = MovieDetailViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.similar_movies_fragment, container, false)
        var similarMoviesList = movieDetailViewModel.getSimilarMoviesByTitle(movieName)
        var similarMoviesRV= view.findViewById<RecyclerView>(R.id.listSimilar)
        similarMoviesRV.layoutManager = LinearLayoutManager(activity)
        var similarMoviesRVSimpleAdapter = SimpleStringAdapter(similarMoviesList)
        similarMoviesRV.adapter=similarMoviesRVSimpleAdapter

        return view
    }


}