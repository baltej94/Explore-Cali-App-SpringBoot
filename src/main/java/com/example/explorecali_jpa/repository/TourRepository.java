package com.example.explorecali_jpa.repository;

import com.example.explorecali_jpa.model.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.explorecali_jpa.model.Tour;

import java.util.List;

/**
 * Tour Repository Interface
 *  extends JpaRepository with the entity type and primary key type as type arguments
 *  <Entity_Type, unique_id>
 */

public interface TourRepository extends JpaRepository<Tour, Integer> {
    public List<Tour> findByDifficulty(Difficulty difficulty);
    List<Tour> findByTourPackageCode(String code);


}
