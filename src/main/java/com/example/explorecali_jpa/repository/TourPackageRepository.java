package com.example.explorecali_jpa.repository;

import com.example.explorecali_jpa.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "packages", collectionResourceRel = "packages")
public interface TourPackageRepository extends JpaRepository<TourPackage, String> {
    Optional<TourPackage> findByName(String name);
}
