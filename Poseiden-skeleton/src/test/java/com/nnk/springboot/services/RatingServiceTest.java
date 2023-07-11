package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    private Rating rating;

    @BeforeEach
    public void setup() {
        rating = new Rating();
        rating.setMoodysRating("AAA");
        rating.setSandPRating("BBB");
        rating.setFitchRating("CCC");
        rating.setOrderNumber(123);
    }

    @Test
    public void getAllRatings() {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating));

        List<Rating> ratings = ratingService.getAllRatings();

        assertEquals(1, ratings.size());
        assertEquals(rating, ratings.get(0));
    }

    @Test
    public void getRatingById() {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating result = ratingService.getRatingById(1);

        assertEquals(rating, result);
    }

    @Test
    public void getRatingById_NotFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            ratingService.getRatingById(1);
        });
    }

    @Test
    public void updateRating() {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating result = ratingService.updateRating(1, rating);

        assertEquals(rating, result);
    }

    @Test
    public void saveRating() {
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating result = ratingService.saveRating(rating);

        assertEquals(rating, result);
    }

    @Test
    public void deleteRating() {
        doNothing().when(ratingRepository).deleteById(1);

        ratingService.deleteRating(1);

        verify(ratingRepository, times(1)).deleteById(1);
    }
}
