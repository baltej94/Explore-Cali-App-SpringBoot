package com.example.explorecali_jpa.repository;

import com.example.explorecali_jpa.model.TourRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * exported = false because we do not want this repo to be exposed to Spring Data REST
 */

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating,Integer>, CrudRepository<TourRating,Integer> {

List<TourRating> findByTourId(Integer tourId);

Optional<TourRating> findByTourIdAndCustomerId(Integer tourId, Integer customerId);
}
