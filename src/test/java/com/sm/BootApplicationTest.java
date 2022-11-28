package com.sm;

import com.sm.dto.MovieDto;
import com.sm.entity.Movie;
import com.sm.mapper.MovieMapper;
import com.sm.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTest {

    public static String MOVIE_1 = "Iron Man";
    public static String MOVIE_2 = "Evil";
    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void setUp() throws Exception {
        movieRepository.save(new Movie(MOVIE_1, 5));
        movieRepository.save(new Movie(MOVIE_2, 4));
    }

    /**
     * Two concurrent users are trying to update same row at same time
     *
     */
    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testConcurrencyWriting() {
        assertEquals("Number of movies in DB.", 2, movieRepository.count());

        Movie ironManForUserOne = movieRepository.findByTitle(MOVIE_1);
        Movie ironManForUserTwo = movieRepository.findByTitle(MOVIE_1);

        MovieDto firstMovieDtoForUserOne = MovieMapper.of(ironManForUserOne);
        MovieDto secondMovieDtoForUserTwo = MovieMapper.of(ironManForUserTwo);

        // Modify field
        firstMovieDtoForUserOne.setRating(1);
        secondMovieDtoForUserTwo.setRating(0);

        Movie updateFirstMovieForUserOne = MovieMapper.of(firstMovieDtoForUserOne);
        Movie updateSecondMovieForUserTwo = MovieMapper.of(secondMovieDtoForUserTwo);

        // The versions for both movies are 0.
        assertEquals(0, updateFirstMovieForUserOne.getVersion().intValue());
        assertEquals(0, updateSecondMovieForUserTwo.getVersion().intValue());

        // save first updated movie
        movieRepository.save(updateFirstMovieForUserOne);

        // Once first movie is saved then version will be checked by internally and if it is different then
        // it will throw Exception
        movieRepository.save(updateSecondMovieForUserTwo);
    }


}

