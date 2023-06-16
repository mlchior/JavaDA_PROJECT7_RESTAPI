package com.nnk.springboot.controllers;

import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> rating = ratingService.getAllRatings();
        model.addAttribute("ratings", rating);
        Logger.info("All ratings retrieved");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        Logger.info("Add rating form displayed");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in rating validation");
            return "rating/add";
        }
        ratingService.saveRating(rating);
        Logger.info("Rating saved");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        Logger.info("Update form for rating with id {} displayed", id);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in updating rating with id {}", id);
            return "rating/update";
        }
        ratingService.saveRating(rating);
        Logger.info("Rating with id {} updated", id);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id);
        ratingService.deleteRating(id);
        Logger.info("Rating with id {} deleted", id);
        return "redirect:/rating/list";
    }
}
