package com.example.cinaeste

import com.example.cinaeste.data.Movie
import com.example.cinaeste.data.MovieRepository
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.*

import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.not
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.hamcrest.CoreMatchers.`is` as Is

import org.junit.Test

class RepositoryUnitTest {
    @Test
    fun testGetFavoriteMovies(){
        val movies = MovieRepository.getFavoriteMovies()
        assertEquals(movies.size,5)
        assertThat(movies,hasItem<Movie>(hasProperty("title", Is("Fatherhood"))))
        assertThat(movies,not(hasItem<Movie>(hasProperty("title", Is("The Contractor")))))

    }
    @Test
    fun testRecentMovies(){
        val movies = MovieRepository.getRecentMovies()
        assertEquals(movies.size,5)
        assertThat(movies,hasItem<Movie>(hasProperty("title", Is("Love Tactics"))))
        assertThat(movies,not(hasItem<Movie>(hasProperty("title", Is("Pride and prejudice")))))

    }

}