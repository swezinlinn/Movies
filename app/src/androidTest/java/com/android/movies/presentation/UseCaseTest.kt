package com.android.movies.presentation

import com.android.movie.util.TestUtil
import com.android.movies.data.repository.MovieRepo
import com.android.movies.domain.model.MovieDetail
import com.android.movies.domain.usecase.GetMovieDetail
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UseCaseTest {
    private lateinit var getMovie: GetMovieDetail

    private lateinit var mockRepo: MovieRepo

    @Before
    fun setUp() {
        mockRepo = mock()
        getMovie = GetMovieDetail(mockRepo)
    }

    /**
     * Check Use Case call getMovieDetail function.
     * */
    @Test
    fun buildUseCaseObservable() {
        getMovie.provideSingle("tt0036621")
        verify(mockRepo).getMovieDetail("tt0036621")
    }

    /**
     * Check Use Case implement successfully.
     * */
    @Test
    fun buildUseCaseObservableComplete() {
        stubOperator(Single.just(TestUtil.createMovieDetail("tt0036621")))
        val testObserver = getMovie.provideSingle("tt0036621").test()
        testObserver.assertComplete()
    }

    /**
     * Check Use Case return successfully with data
     * */
    @Test
    fun buildUseCaseObservableReturnData() {
        val movie = TestUtil.createMovieDetail("tt0036621")
        stubOperator(Single.just(movie))
        val testObserver = getMovie.provideSingle("tt0036621").test()
        testObserver.assertValue(movie)
    }

    private fun stubOperator(single: Single<MovieDetail>) {
        whenever(mockRepo.getMovieDetail("tt0036621")).thenReturn(single)
    }
}