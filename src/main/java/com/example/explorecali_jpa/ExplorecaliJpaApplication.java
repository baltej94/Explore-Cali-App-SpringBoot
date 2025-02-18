package com.example.explorecali_jpa;

import com.example.explorecali_jpa.model.Region;
import com.example.explorecali_jpa.service.TourPackageService;
import com.example.explorecali_jpa.service.TourService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.explorecali_jpa.model.Difficulty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.repeat;

@SpringBootApplication
public class ExplorecaliJpaApplication implements CommandLineRunner {
	private final String TOUR_IMPORT_FILE = "ExploreCalifornia.json";

	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService;

	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createTourAllPackages();
		System.out.println("Persisted Packages = " + tourPackageService.total());
		createToursFromFile(TOUR_IMPORT_FILE);
		System.out.println("Persisted Tours = " + tourService.total());
		System.out.println(repeat("*", 50));
		//	System.out.println(tourService.lookupByDifficulty(Difficulty.Medium));
		tourService.lookupByDifficulty(Difficulty.Medium).forEach(tour -> System.out.println("/n"+ tour.getTitle()));
	}

	private void createTourAllPackages() {
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
	}

	private void createToursFromFile(String fileToImport) throws IOException {
		TourFromFile.read(fileToImport).forEach(tourPackage ->
				tourService.createTour(
				tourPackage.packageName,
				tourPackage.title,
				tourPackage.description,
				tourPackage.blurb,
				tourPackage.price,
				tourPackage.length,
				tourPackage.bullets,
				tourPackage.keywords,
				Difficulty.valueOf(tourPackage.difficulty),
				Region.findByLabel(tourPackage.region())
						)
		);

	}
	record TourFromFile(String packageName, String title, String description,
						String blurb, Integer price, String length, String bullets,
						String keywords, String difficulty, String region) {
		static List<TourFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().readValue(new File(fileToImport),
					new TypeReference<List<TourFromFile>>() {});
		}
}
}
