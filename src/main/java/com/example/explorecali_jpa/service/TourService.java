package com.example.explorecali_jpa.service;

import com.example.explorecali_jpa.model.Difficulty;
import com.example.explorecali_jpa.model.Region;
import com.example.explorecali_jpa.repository.TourPackageRepository;
import com.example.explorecali_jpa.repository.TourRepository;
import org.springframework.stereotype.Service;
import com.example.explorecali_jpa.model.Tour;
import com.example.explorecali_jpa.model.TourPackage;

import java.util.List;

@Service
public class TourService {
    private TourPackageRepository tourPackageRepository;
    private TourRepository tourRepository;

    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String tourPackageName, String title, String description,
                           String blurb, Integer price, String duration, String bullets,
                           String keywords, Difficulty difficutly, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exist for id " + tourPackageName));

        return  tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficutly, region));
    }

    public List<Tour> lookupByDifficulty(Difficulty difficulty) {
        return tourRepository.findByDifficulty(difficulty);
    }

    public List<Tour>lookUpByPackage(String tourPackageCode) {
        return tourRepository.findByTourPackageCode(tourPackageCode);
    }
    public long total() {
        return tourRepository.count();
    }
}
