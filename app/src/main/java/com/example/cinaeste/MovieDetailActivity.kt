package com.example.cinaeste

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinaeste.R
import com.example.cinaeste.data.Movie
import com.example.cinaeste.view.*
import com.example.cinaeste.viewmodel.MovieDetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var movie:Movie
    private lateinit var title:TextView
    private lateinit var overview:TextView
    private lateinit var releaseDate:TextView
    private lateinit var genre:TextView
    private lateinit var website:TextView
    private lateinit var poster:ImageView
    private lateinit var button:Button
    private var movieDetailViewModel = MovieDetailViewModel()

    private lateinit var bottomNavigation : BottomNavigationView

    //Listener za click
    private val mOnItemSelectedListener = NavigationBarView.OnItemSelectedListener{ item ->
        when (item.itemId) {
            R.id.navigation_actors -> {
                val actorsFragment = ActorsFragment(movie.title)
                openFragment(actorsFragment)
                return@OnItemSelectedListener true
            }
            R.id.navigation_similarMovies -> {
                val similarMoviesFragment = SimilarMoviesFragment(movie.title)
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
        bottomNavigation = findViewById(R.id.detailNavigation)
        bottomNavigation.setOnItemSelectedListener(mOnItemSelectedListener)



        var extras = intent.extras
        if(extras!=null){
            movie=movieDetailViewModel.getMovieByTitle(extras.getString("movie_title",""))
            populateDetails()
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
        genre.text=movie.genre
        overview.text=movie.overview
        website.text=movie.homepage
        val context:Context=poster.context
        var id :Int=context.getResources().getIdentifier(movie.genre,"drawable",context.packageName)
        if(id==0)id=context.resources.getIdentifier("movie_icon","drawable",context.packageName)
        poster.setImageResource(id)
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
}