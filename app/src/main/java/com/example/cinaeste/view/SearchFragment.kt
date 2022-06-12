package com.example.cinaeste.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.database.CursorWindowCompat.create
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.MovieDetailActivity
import com.example.cinaeste.R
import com.example.cinaeste.data.Movie
import com.example.cinaeste.viewmodel.MovieListViewModel
import java.net.URI.create

class SearchFragment : Fragment() {
    private lateinit var searchText : EditText
    private lateinit var searchButton : AppCompatImageButton
    private lateinit var resultsRV : RecyclerView
    private lateinit var resultsAdapter : MovieListAdapter
    private lateinit var movieListViewModel : MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.search_fragment, container, false)
        searchText = view.findViewById(R.id.searchText)
        movieListViewModel = context?.let { MovieListViewModel(it) }!!
        arguments?.getString("search")?.let {
            searchText.setText(it)
        }
        searchButton = view.findViewById(R.id.searchButton)
        resultsRV = view.findViewById(R.id.resultsRV)
        resultsRV.layoutManager=GridLayoutManager(activity,2)
        resultsAdapter = MovieListAdapter(arrayListOf()){ movie,view1,view2 -> showMovieDetails(movie,view1,view2) }
        resultsRV.adapter=resultsAdapter

        searchButton.setOnClickListener{
            onClick()
        }

        return view
    }

    private fun showMovieDetails(movie: Movie, view1: View, view2:View) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_id", movie.id)
        }
        val options = ActivityOptions
            .makeSceneTransitionAnimation(activity,  Pair.create(view1, "poster"),
                Pair.create(view2, "title"))
        startActivity(intent,options.toBundle())
    }
    private fun onClick() {
        val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
        toast.show()
        movieListViewModel.search(searchText.text.toString(),onSuccess = ::onSuccess,
            onError = ::onError)
    }
    fun searchDone(movies : List<Movie>){
        val toast = Toast.makeText(context,"Search done",Toast.LENGTH_SHORT)
        toast.show()
        resultsAdapter.updateMovies(movies)

    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
    fun onSuccess(movies:List<Movie>){
        val toast = Toast.makeText(context, "Upcoming movies found", Toast.LENGTH_SHORT)
        toast.show()
        resultsAdapter.updateMovies(movies)
    }

    companion object {
        fun newInstance(search : String): SearchFragment = SearchFragment().apply {
            arguments = Bundle().apply {
                putString("search",search)
            }
        }
    }
}