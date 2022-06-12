package com.example.cinaeste.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.data.Cast
import com.example.cinaeste.R
import com.example.cinaeste.viewmodel.MovieDetailViewModel

class ActorsFragment(movieName : String,movieId:Long?,favourite:Boolean) : Fragment(){
    private val favourite = favourite
    private var movieName : String = movieName
    private var movieId:Long? = movieId
    private lateinit var movieRV:RecyclerView
    private var actorsList= listOf<Cast>()
    private lateinit var actorsRVSimpleAdapter:SimpleCastStringAdapter
    private var movieDetailViewModel = MovieDetailViewModel()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.actors_fragment, container, false)
        movieRV = view.findViewById(R.id.listActors)
        //ako je naslov
       // actorsList = movieName?.let { movieDetailViewModel.getActorsByTitle(it) }!!
        movieRV.layoutManager = LinearLayoutManager(activity)
        actorsRVSimpleAdapter = SimpleCastStringAdapter(actorsList)
        movieRV.adapter = actorsRVSimpleAdapter
        //ako je id
        if(favourite){
            movieId?.let { movieDetailViewModel.getActorsByIdDB(requireContext(),it,onSuccess = ::onSuccess,onError = ::onError) }
        }else{
            movieId?.let { movieDetailViewModel.getActorsById(it,onSuccess = ::onSuccess,
                onError = ::onError) }
        }
        return view
    }
    fun onSuccess(actors:List<Cast>){
        actorsList=actors
        actorsRVSimpleAdapter.list=actors
        actorsRVSimpleAdapter.notifyDataSetChanged();
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }

}