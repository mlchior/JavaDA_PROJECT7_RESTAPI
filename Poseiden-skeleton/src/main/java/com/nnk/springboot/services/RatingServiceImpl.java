package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAllRatings() {
        Logger.info("Retrieving all Ratings");
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Integer id) {
        Logger.info("Retrieving Rating with id {}", id);
        return ratingRepository.findById(id).orElseThrow(() -> {
            Logger.error("Invalid rating id: {}", id);
            return new IllegalArgumentException("Invalid rating id: " + id);
        });
    }

    @Override
    public Rating updateRating(Integer id, Rating rating) {
        Logger.info("Updating Rating with id {}", id);
        Rating existingRating = ratingRepository.findById(id).orElseThrow(() -> {
            Logger.error("Invalid rating id: {}", id);
            return new IllegalArgumentException("Invalid rating id: " + id);
        });
        if (existingRating != null) {
            existingRating.setMoodysRating(rating.getMoodysRating());
            existingRating.setSandPRating(rating.getSandPRating());
            existingRating.setFitchRating(rating.getFitchRating());
            existingRating.setOrderNumber(rating.getOrderNumber());
            Logger.info("Rating with id {} updated successfully", id);
            return ratingRepository.save(existingRating);
        }
        Logger.error("Failed to update Rating with id {}", id);
        return null;
    }

    @Override
    public Rating saveRating(Rating rating) {
        Logger.info("Saving new Rating");
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Integer id) {
        Logger.info("Deleting Rating with id {}", id);
        ratingRepository.deleteById(id);
    }
}
