package com.example.cinaeste.data

object ActorMovieRepository {
    fun getActorMovies(): Map<String,List<String>>{
        return movieActors()
    }
}