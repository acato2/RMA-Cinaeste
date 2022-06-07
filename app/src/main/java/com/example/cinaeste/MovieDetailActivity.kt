package com.example.cinaeste

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cinaeste.data.Movie
import com.example.cinaeste.view.*
import com.example.cinaeste.viewmodel.MovieDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MovieDetailActivity : AppCompatActivity() {
    private var movieDetailViewModel =  MovieDetailViewModel(this@MovieDetailActivity::movieRetrieved,null,null)


    private lateinit var movie:Movie
    private lateinit var title:TextView
    private lateinit var overview:TextView
    private lateinit var releaseDate:TextView
    private lateinit var genre:TextView
    private lateinit var website:TextView
    private lateinit var poster:ImageView
    private lateinit var button:Button
    private lateinit var backdrop : ImageView
    private lateinit var addFavorite : Button
    private val posterPath= "https://image.tmdb.org/t/p/w780"
    private val backdropPath= "https://image.tmdb.org/t/p/w500"


    private lateinit var bottomNavigation : BottomNavigationView

    //Listener za click
    private val mOnItemSelectedListener = NavigationBarView.OnItemSelectedListener{ item ->
        when (item.itemId) {
            R.id.navigation_actors -> {
                val actorsFragment = ActorsFragment(movie.title,movie.id)
                openFragment(actorsFragment)
                return@OnItemSelectedListener true
            }
            R.id.navigation_similarMovies -> {
                val similarMoviesFragment = SimilarMoviesFragment(movie.title,movie.id)
                openFragment(similarMoviesFragment)
                return@OnItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        title=findViewById(R.id.movie_title)
        overview=findViewById(R.id.movie_overview)
        releaseDate=findViewById(R.id.movie_release_date)
        genre=findViewById(R.id.movie_genre)
        website=findViewById(R.id.movie_website)
        poster=findViewById(R.id.movie_poster)
        backdrop = findViewById(R.id.movie_backdrop)
        bottomNavigation = findViewById(R.id.detailNavigation)
        bottomNavigation.setOnItemSelectedListener(mOnItemSelectedListener)
        addFavorite=findViewById(R.id.addFavorites)



        var extras = intent.extras
        if(extras!=null){
            if (extras.containsKey("movie_title")) {
                movie = movieDetailViewModel.getMovieByTitle(extras.getString("movie_title", ""))
                populateDetails()
            }
            else if (extras.containsKey("movie_id")){
                movieDetailViewModel.getMovieDetails(extras.getLong("movie_id"))
            }
        }

        else{
            finish()
        }
        website.setOnClickListener{
            showWebsite()
        }
        title.setOnClickListener{
            openYouTube()
        }
        addFavorite.setOnClickListener {
            writeDB()
        }


    }
    //Funkcija za izmjenu fragmenta
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.actorSimilarContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showOverView() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,movie.overview)
            type="text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent,null)
        try{
            startActivity(sendIntent)
        }catch(e:ActivityNotFoundException){
            //nesto
        }

    }

    private fun openYouTube() {
        val intent = Intent(Intent.ACTION_SEARCH)
        intent.setPackage("com.google.android.youtube")
        intent.putExtra("query","${title.text} trailer" )
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try{
            startActivity(intent)
        }catch(e:ActivityNotFoundException){
            //nesto
        }

    }

    fun populateDetails(){
        title.text=movie.title
        releaseDate.text=movie.releaseDate
        overview.text=movie.overview
        website.text=movie.homepage
        val context:Context=poster.context
        var id = 0;
        if (id===0) id=context.getResources()
            .getIdentifier("movie_icon", "drawable", context.getPackageName())
        Glide.with(context)
            .load(posterPath + movie.posterPath)
            .placeholder(R.drawable.movie_icon)
            .error(id)
            .fallback(id)
            .into(poster);
        var backdropContext: Context = backdrop.getContext()
        Glide.with(backdropContext)
            .load(backdropPath + movie.backdropPath)
            .centerCrop()
            .placeholder(R.drawable.background_image)
            .error(R.drawable.background_image)
            .fallback(R.drawable.background_image)
            .into(backdrop);

    }
    fun showWebsite(){
        val webIntent: Intent = Uri.parse(movie.homepage).let{webpage ->
            Intent(Intent.ACTION_VIEW,webpage)}
        try{
            startActivity(webIntent)
        }catch(e:ActivityNotFoundException){
            //nesto
        }
    }
    fun movieRetrieved(movie:Movie){
        this.movie =movie;
        populateDetails()
    }
    fun writeDB(){
        movieDetailViewModel.writeDB(applicationContext,this.movie,onSuccess = ::onSuccess1,
            onError = ::onError)
    }
    fun onSuccess1(message:String){
        val toast = Toast.makeText(applicationContext, "Spaseno", Toast.LENGTH_SHORT)
        toast.show()
        addFavorite.visibility= View.GONE
    }
    fun onError() {
        val toast = Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }


}


