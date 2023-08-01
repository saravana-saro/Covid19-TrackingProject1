package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LocationStates;

@Repository
public interface CovidDataRepository extends JpaRepository<LocationStates, Long> {

	List<LocationStates> findTopNByLatestTotalDeaths(int count);

	LocationStates findByCountry(String countryName);

	Optional<LocationStates> findById(int countryid);
       	
}