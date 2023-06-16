package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RatingServiceTest {

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRatings() {
        List<Rating> ratingList = new ArrayList<>();
        Rating rating1 = new Rating("MoodysRating1", "SandPRating1", "FitchRating1", 10);
        Rating rating2 = new Rating("MoodysRating2", "SandPRating2", "FitchRating2", 20);

        ratingList.add(rating1);
        ratingList.add(rating2);

        when(ratingRepository.findAll()).thenReturn(ratingList);

        List<Rating> result = ratingService.getAllRatings();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetRatingById() {
        Rating rating = new Rating("MoodysRating", "SandPRating", "FitchRating", 10);
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));

        Rating foundRating = ratingService.getRatingById(1);

        assertNotNull(foundRating);
        assertEquals(rating.getMoodysRating(), foundRating.getMoodysRating());
    }

    @Test
    public void testSaveRating() {
        Rating rating = new Rating("MoodysRating", "SandPRating", "FitchRating", 10);
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating savedRating = ratingService.saveRating(rating);

        assertNotNull(savedRating);
        assertEquals(rating.getMoodysRating(), savedRating.getMoodysRating());
    }

    @Test
    public void testUpdateRating() {
        Rating rating = new Rating("MoodysRating", "SandPRating", "FitchRating", 10);
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating updatedRating = ratingService.updateRating(1, rating);

        assertNotNull(updatedRating);
        assertEquals(rating.getMoodysRating(), updatedRating.getMoodysRating());
    }

    @Test
    public void testDeleteRating() {
        Rating rating = new Rating("MoodysRating", "SandPRating", "FitchRating", 10);
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));
        doNothing().when(ratingRepository).deleteById(anyInt());

        ratingService.deleteRating(1);

        verify(ratingRepository, times(1)).deleteById(anyInt());
    }
}
