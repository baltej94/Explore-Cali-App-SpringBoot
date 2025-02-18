package com.example.explorecali_jpa.service;

import com.example.explorecali_jpa.model.TourPackage;
import com.example.explorecali_jpa.repository.TourPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository){
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name){
     return tourPackageRepository.findById(code)
             .orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    public List<TourPackage> findAll(){
        return tourPackageRepository.findAll();
    }

    public long total(){
        return tourPackageRepository.count();
    }
}
