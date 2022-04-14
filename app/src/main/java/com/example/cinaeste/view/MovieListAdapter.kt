package com.example.cinaeste.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinaeste.data.Movie
import com.example.cinaeste.R

class MovieListAdapter( private var movies: List<Movie>,
                        private val onItemClicked: (movie:Movie,view1:View,view2:View) -> Unit):
      RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
    //definisemo reference na sve elemente
    inner class MovieViewHolder(view: View):RecyclerView.ViewHolder(view){
        val movieImage : ImageView = view.findViewById(R.id.movieImage)
        val movieTitle : TextView = view.findViewById(R.id.movieTitle)

    }

    //kreiraj novi view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MovieViewHolder(view)
    }
    //izmjeni sadrzaj viewa
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieTitle.text=movies[position].title
        val genreMatch : String = movies[position].genre
        val context : Context = holder.movieImage.getContext()
        var id : Int = context.getResources().getIdentifier(genreMatch,"drawable",context.getPackageName())
        if(id==0)id=context.getResources().getIdentifier("movie_icon","drawable",context.getPackageName())
        holder.movieImage.setImageResource(id)
        holder.itemView.setOnClickListener{ onItemClicked(movies[position],holder.movieImage,holder.movieTitle) }

    }

    override fun getItemCount(): Int = movies.size
    fun updateMovies(movies: List<Movie>) {
        this.movies=movies
        notifyDataSetChanged()

    }
}