package com.example.explorecali_jpa.web;

import com.example.explorecali_jpa.model.TourRating;
import com.example.explorecali_jpa.service.TourRatingService;

import jakarta.servlet.annotation.HttpConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

    @Autowired
    private TourRatingService tourRatingService;

    public TourRatingController(TourRatingService tourRatingService){
        this.tourRatingService = tourRatingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingDto createTourRating(@PathVariable(value = "tourId") int tourId,
                                      @RequestBody @Valid RatingDto ratingDto) {
        TourRating rating = tourRatingService.createNew(tourId, ratingDto.getCustomerId(),
                ratingDto.getScore(), ratingDto.getComment());
        return new RatingDto(rating);
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        List<TourRating> tourRatings = tourRatingService.lookupRatings(tourId);
        return tourRatings.stream().map(RatingDto::new).toList();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String return404(NoSuchElementException exception) {
        return exception.getMessage();
    }
}
