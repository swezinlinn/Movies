package com.android.movies.data

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.android.movie.util.TestUtil
import com.android.movies.data.remote.db.AppDatabase
import com.android.movies.data.remote.db.entity.MovieEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var mDatabase: AppDatabase

    @Before
    fun createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun isPhotoListEmpty(){
        val myScope = GlobalScope
        myScope.launch {
            assertEquals(0, mDatabase.movieDao.getSearchedMovieList("Aventure").size)
        }
    }


    @Test
    @Throws(Exception::class)
    fun insertMovie() {
        val myScope = GlobalScope
        myScope.launch {
            val photo: List<MovieEntity> = TestUtil.createMovieList(6)
            val insertedPhoto = mDatabase.movieDao.insertAll(photo)
            assertNotNull(insertedPhoto)
        }
    }


    @Test
    @Throws(Exception::class)
    fun insertMovieAndLoadByTitle() {
        val myScope = GlobalScope
        val photo: List<MovieEntity> = TestUtil.createMovieList(6)
        myScope.launch {
            mDatabase.movieDao.insertAll(photo)
            val movieLoadedByName = mDatabase.movieDao.getSearchedMovieList("Aventure")
            assertEquals(movieLoadedByName, equalTo(photo))
        }
    }

}