package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;

     @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
}
    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
    @Override
    public Rating getRatingById(Integer id) {
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating id: " + id));

    }
    @Override
    public Rating updateRating(Integer id, Rating rating) {
        Rating existingRating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating id: " + id));
        if(existingRating != null){
            existingRating.setMoodysRating(rating.getMoodysRating());
            existingRating.setSandPRating(rating.getSandPRating());
            existingRating.setFitchRating(rating.getFitchRating());
            existingRating.setOrderNumber(rating.getOrderNumber());
            return ratingRepository.save(existingRating);
        }
        return null;
    }
    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);

    }
    @Override
    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
        }
