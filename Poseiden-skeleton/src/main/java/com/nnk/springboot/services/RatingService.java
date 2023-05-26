package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RatingService {

    public void deleteRating(Integer id);

    public Rating getRatingById(Integer id);

    public Rating updateRating(Integer id, Rating rating);

    public Rating saveRating(Rating rating);

    public List<Rating> getAllRatings();
}
